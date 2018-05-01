/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.warp

import org.bukkit.Location
import org.bukkit.Material
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 12:13.
 * Last edit 01.05.2018
 */
data class SimpleWarp(override val uuid: UUID, override val location: Location, override val material: Material, override val lore: MutableList<String>, override val name: String) : Warp