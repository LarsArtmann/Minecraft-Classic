/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.inventory

import de.lartyhd.spigot.classic.shopwarps.Injector
import de.lartyhd.spigot.classic.shopwarps.builder.InventoryBuilder
import de.lartyhd.spigot.classic.shopwarps.builder.ItemBuilder
import de.lartyhd.spigot.classic.shopwarps.warp.Warp
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 10:54.
 * Last edit 01.05.2018
 */
object WarpsInventory {

	private val main: InventoryBuilder
	//    private val settings: InventoryBuilder
//    private val create: InventoryBuilder
	val warps: MutableList<Warp> = ArrayList()

	init {
		val glass = ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§0").build()
		/*main = InventoryBuilder(54, "§9Shops §8- §9Main")
				.fillWith(glass, 0, 8)
				.fillWith(glass, 45, 53)
				.setItem(4, ItemBuilder(Material.SIGN)
						.setName("§9Infos")
						.setLore(listOf(
								" ",
								"§7Von §bLars Artmann §8| §bLartyHD",
								"§7für §bClassic §7programmiert",
								"",
								"§7Danke an §bTerra §7und §bAyumu §7für das coole §bProjekt"
						))
						.build())
				.setItem(49, ItemBuilder(Material.REDSTONE_COMPARATOR, 1.toShort())
						.setName("§9Einstellungen")
						.build())
		settings = InventoryBuilder(InventoryType.HOPPER, "§9Shops §8- §9Settings")
				.fillWith(glass)
				.setItem(0, ItemBuilder(Material.INK_SACK, 1.toShort())
						.setName("§9Warp löschen")
						.build())
				.setItem(2, ItemBuilder(Material.INK_SACK, 8.toShort())
						.setName("§9Warp Einstellungen anpassen")
						.build())
				.setItem(4, ItemBuilder(Material.INK_SACK, 10.toShort())
						.setName("§9Warp neu erstellen")
						.build())
		create = InventoryBuilder(InventoryType.DISPENSER, "§9Shops §8- §9Create")
				.fillWith(glass)
				.setItem(4, ItemBuilder(Material.INK_SACK, 10.toShort())
						.setName("§9Neuen Warp erstellen")
						.build())*/
		main = InventoryBuilder(54, "§9Shops")
				.fillWith(glass, 0, 8)
				.fillWith(glass, 45, 53)
				.setItem(4, ItemBuilder(Material.SIGN)
						.setName("§9Infos")
						.setLore(listOf(
								" ",
								"§7Von §bLars Artmann §8| §bLartyHD",
								"§7für §bClassic §7programmiert",
								"",
								"§7Danke an §bTerra §7und §bAyumu §7für das coole §bProjekt"
						))
						.build())
				.setItem(46, ItemBuilder(Material.LEGACY_INK_SACK, 1.toShort())
						.setName("§cLösche deinen Warp")
						.build())
				.setItem(52, ItemBuilder(Material.LEGACY_INK_SACK, 10.toShort())
						.setName("§9Setze deinen eigenen Warp")
						.addLore("§7Setzt den Warp auf deine aktuelle Location")
						.build())
	}

	fun removeAndUpdateWarps(uuid: UUID) {
		for (warp in ArrayList(warps)) if (warp.uuid == uuid) {
			warps.remove(warp)
			updateWarps()
		}
	}

	private fun remove(uuid: UUID) {
		for (warp in ArrayList(warps)) if (warp.uuid == uuid) warps.remove(warp)
	}

	fun add(warp: Warp) {
		remove(warp.uuid)
		warps.add(warp)
		updateWarps()
	}

	fun updateWarps() {
		updateInventory()
		setWarps()
	}

	private fun updateInventory(warps: List<Warp> = this.warps) {
		main.replaceWith(ItemStack(Material.AIR), 9, 44)
		for (i in 9 until warps.size + 9) main.setItem(i, warps[i - 9].getItemWithUUIDLore())
	}

	private fun setWarps() = Injector.configManager.setWarps(warps)

	fun getWarp(name: String): Warp? = warps.find { it.name == name }

	fun getWarp(uuid: UUID): Warp? = warps.find { it.uuid == uuid }

	fun openMain(humanEntity: HumanEntity) =
			openInventory(humanEntity, main.apply { updateInventory(getSortedWarps()) })
//
//    fun openSettings(humanEntity: HumanEntity) = openInventory(humanEntity, settings)
//
//    fun openCreate(humanEntity: HumanEntity) = openInventory(humanEntity, create)

	fun openInventory(humanEntity: HumanEntity, inventoryBuilder: InventoryBuilder): InventoryView =
			humanEntity.openInventory(inventoryBuilder.build())

	private fun getSortedWarps() = warps.sortedBy { Bukkit.getOfflinePlayer(it.uuid)?.lastPlayed ?: -1 }.reversed()
}