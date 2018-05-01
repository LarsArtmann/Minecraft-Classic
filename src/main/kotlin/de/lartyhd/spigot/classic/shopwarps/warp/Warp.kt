/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.warp

import de.lartyhd.spigot.classic.shopwarps.builder.ItemBuilder
import org.bukkit.Location
import org.bukkit.Material

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 11:24.
 * Last edit 01.05.2018
 */
interface Warp {

    val location: Location?
    val material: Material?
    val lore: List<String>?
    val name: String?

    fun getItem() = ItemBuilder(material!!).setLore(lore!!).setName("§9$name").build()

}