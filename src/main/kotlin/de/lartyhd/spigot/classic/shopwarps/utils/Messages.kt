/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.utils

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 23.05.2018 18:45.
 * Last edit 23.05.2018
 */
enum class Messages(private val message: String) {

    NAME("Shops"),
    PREFIX("§8[§b$NAME§8] §r"),
    UNCOLORED_PREFIX("[$NAME] ");

    override fun toString() = message


}