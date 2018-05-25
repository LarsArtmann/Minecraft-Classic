/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.api.interfaces.permissions.tester

import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2018 07:24.
 * Last edit 24.05.2018
 */
interface PermissionsTester {

    fun hasPermission(target: CommandSender, permission: String): Boolean

    fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit)

    fun hasPermission(target: CommandSender, permission: String, permissionMessage: String, lambda: () -> Unit)

}