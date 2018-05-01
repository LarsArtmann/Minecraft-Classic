/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps

import org.bukkit.Location

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 08:13.
 * Last edit 01.05.2018
 */
interface Warps {

    fun getWarp(name: String): Location?

    fun removeWarp(name: String): Location?

    fun clearWarps()

}