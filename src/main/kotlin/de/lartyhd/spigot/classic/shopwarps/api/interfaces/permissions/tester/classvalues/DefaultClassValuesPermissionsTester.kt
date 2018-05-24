/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.api.interfaces.permissions.tester.classvalues

import de.lartyhd.spigot.classic.shopwarps.api.interfaces.permissions.tester.DefaultPermissionsTester
import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2018 07:28.
 * Last edit 24.05.2018
 */
interface DefaultClassValuesPermissionsTester : DefaultPermissionsTester, ClassValuesPermissionsTester {

    val permission: String
    val permissionMessage: String

    override fun hasPermission(target: CommandSender): Boolean = hasPermission(target, permission)

    override fun hasPermission(target: CommandSender, lambda: () -> Unit) = hasPermission(target, permission, lambda)

    override fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit) = hasPermission(target, permission, permissionMessage, lambda)

}