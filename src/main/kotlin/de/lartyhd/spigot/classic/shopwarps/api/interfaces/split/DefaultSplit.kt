/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.api.interfaces.split

import de.lartyhd.spigot.classic.shopwarps.api.interfaces.Default

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2018 07:32.
 * Last edit 24.05.2018
 */
interface DefaultSplit : Split, Default {
    override fun split(value: String, key: String) = value.split(key).dropLastWhile { it.isEmpty() }.toTypedArray()
}