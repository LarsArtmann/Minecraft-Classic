/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.inventory

import de.lartyhd.spigot.classic.shopwarps.builder.InventoryBuilder
import de.lartyhd.spigot.classic.shopwarps.builder.ItemBuilder
import de.lartyhd.spigot.classic.shopwarps.config.Configuration
import de.lartyhd.spigot.classic.shopwarps.warp.SimpleWarp
import de.lartyhd.spigot.classic.shopwarps.warp.Warp
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import java.io.File
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 10:54.
 * Last edit 01.05.2018
 */
object WarpsInventory {

    private val inventory: InventoryBuilder
    val warps: MutableList<Warp> = ArrayList()

    init {
        val glass = ItemBuilder(Material.STAINED_GLASS_PANE, 7.toShort()).setName("§0").build()
        inventory = InventoryBuilder(54, "§9Shops").fillWith(glass, 0, 8).fillWith(glass, 45, 53).setItem(4, ItemBuilder(Material.SIGN).setName("§9Infos").setLore(listOf(" ", "§7Von §bLars Artmann §8| §bLartyHD", "§7für §bClassic §7programmiert", "", "§7Danke an Terra für das coole §bProjekt")).build()).setItem(49, ItemBuilder(Material.NETHER_STAR).setName("§9Setze deinen eigenen Warp").addLore("§7Setzt den Warp auf deine aktuelle Location").build())
        getConfig()
        updateWarps()
    }

    fun remove(uuid: UUID) {
        for (warp in warps) if (warp.uuid == uuid) warps.remove(warp)
    }

    fun updateWarps() {
        inventory.fillWith(ItemStack(Material.AIR), 9, 44)
        for (i in 9 until warps.size + 9) inventory.setItem(i, warps[i - 9].getItem())
    }

    fun getWarp(name: String): Warp? {
        for (warp in warps) if (warp.name == name) return warp
        return null
    }

    fun openInventory(humanEntity: HumanEntity): InventoryView = humanEntity.openInventory(inventory.build())

    private fun getConfig() {
        val folder = File("plugins${File.separator}ShopWarps")
        if (!folder.exists()) folder.mkdirs()
        val file = File(folder, "shops.yml")
        if (!file.exists()) file.createNewFile()
        val conf = Configuration(file)
        Runtime.getRuntime().addShutdownHook(Thread({
            for (i in 0 until warps.size) {
                val warp = warps[i]
                val locationPrefix = "shops.$i.location."
                val location = warp.location
                conf.set("shops.$i.uuid", warp.uuid.toString())
                conf.set("${locationPrefix}world", location?.world?.name)
                conf.set("${locationPrefix}X", location?.x)
                conf.set("${locationPrefix}Y", location?.y)
                conf.set("${locationPrefix}Z", location?.z)
                conf.set("${locationPrefix}Yaw", location?.yaw)
                conf.set("${locationPrefix}Pitch", location?.pitch)
                conf.set("shops.$i.material", warp.material?.id)
                conf.set("shops.$i.lore", warp.lore)
                conf.set("shops.$i.name", warp.name)
            }
            conf.save(conf.file)
        }))
        if (conf.get("shops") != null) for (i in 0..Int.MAX_VALUE) {
            if (conf.get("shops.$i") == null) return
            val locationPrefix = "shops.$i.location."
            val uuid: UUID = UUID.fromString(conf.getString("shops.$i.uuid"))
            val location = Location(Bukkit.getWorld(conf.getString("${locationPrefix}world")), conf.getDouble("${locationPrefix}X"), conf.getDouble("${locationPrefix}Y"), conf.getDouble("${locationPrefix}Z"), conf.getDouble("${locationPrefix}Yaw").toFloat(), conf.getDouble("${locationPrefix}Pitch").toFloat())
            val material: Material = Material.getMaterial(conf.getInt("shops.$i.material"))
            val lore: MutableList<String> = conf.getStringList("shops.$i.lore")
            val name: String = conf.getString("shops.$i.name")
            warps.add(SimpleWarp(uuid, location, material, lore, name))
        }
    }

}