/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps

import org.bukkit.Location

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 08:16.
 * Last edit 01.05.2018
 */
class SimpleWarps : Warps {
    private var warps: MutableMap<String, Location> = HashMap()

    override fun getWarp(name: String): Location? = warps[name]

    override fun removeWarp(name: String) = warps.remove(name)

    override fun clearWarps() = warps.clear()

}