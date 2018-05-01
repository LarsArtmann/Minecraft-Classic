/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.warp

import org.bukkit.Location
import org.bukkit.Material

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 12:13.
 * Last edit 01.05.2018
 */
class SimpleWarp(override val location: Location, override val material: Material, override val lore: List<String>, override val name: String) : Warp