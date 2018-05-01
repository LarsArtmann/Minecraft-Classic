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
class NullWarp(override var location: Location? = null, override var material: Material? = null, override var lore: List<String>? = null, override var name: String? = null) : Warp