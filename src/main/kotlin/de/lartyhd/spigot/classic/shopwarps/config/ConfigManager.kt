/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.config

import de.lartyhd.spigot.classic.shopwarps.warp.SimpleWarp
import de.lartyhd.spigot.classic.shopwarps.warp.Warp
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import java.io.File
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2018 13:33.
 * Last edit 02.05.2018
 */
class ConfigManager(val folder: File, fileName: String) {

    val file: File = File(folder, fileName)
    val conf = Configuration(file)

    init {
        if (!folder.exists()) folder.mkdirs()
        if (!file.exists()) file.createNewFile()
    }


    fun addWarps(warps: MutableList<Warp>) {
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

    fun setWarps(warps: List<Warp>) {
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
    }
}