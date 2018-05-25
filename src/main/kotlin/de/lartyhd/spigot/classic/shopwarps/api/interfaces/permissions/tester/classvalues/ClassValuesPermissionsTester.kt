/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.api.interfaces.permissions.tester.classvalues

import de.lartyhd.spigot.classic.shopwarps.api.interfaces.permissions.tester.PermissionsTester
import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2018 07:24.
 * Last edit 24.05.2018
 */
interface ClassValuesPermissionsTester : PermissionsTester {

    fun hasPermission(target: CommandSender): Boolean

    fun hasPermission(target: CommandSender, lambda: () -> Unit)

}