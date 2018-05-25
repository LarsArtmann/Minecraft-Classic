/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.api.interfaces.permissions.tester

import de.lartyhd.spigot.classic.shopwarps.api.interfaces.split.DefaultSplit
import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2018 07:25.
 * Last edit 24.05.2018
 */
interface DefaultPermissionsTester : PermissionsTester, DefaultSplit {

    override fun hasPermission(target: CommandSender, permission: String): Boolean {
        if (permission.isEmpty()) return true
        for (p in split(permission, ";"))
            if (target.hasPermission(p)) return true
        return false
    }

    override fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit) = hasPermission(target, permission, "", lambda)


    override fun hasPermission(target: CommandSender, permission: String, permissionMessage: String, lambda: () -> Unit) {
        when {
            hasPermission(target, permission) -> lambda()
            permissionMessage == "" -> target.sendMessage("§cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.")
            permissionMessage.isNotEmpty() -> for (line in split(permissionMessage.replace("<permission>", permission), "\n")) target.sendMessage(line)
        }
    }

}