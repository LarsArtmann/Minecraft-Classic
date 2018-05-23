/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.warp

import de.lartyhd.spigot.classic.shopwarps.builder.ItemBuilder
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 11:24.
 * Last edit 01.05.2018
 */
interface Warp {
    val uuid: UUID
    val location: Location?
    val material: Material?
    val lore: MutableList<String>
    val name: String?

    fun getItem(): ItemStack {
        return ItemBuilder(material!!).setLore(lore).setName("§9$name").build()
    }

}