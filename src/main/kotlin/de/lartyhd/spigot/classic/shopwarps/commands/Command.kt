/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.lartyhd.spigot.classic.shopwarps.commands

import de.lartyhd.spigot.classic.shopwarps.commands.interfaces.ICommand
import org.bukkit.ChatColor
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.03.2018 03:41.
 * Last edit 22.05.2018
 */
@Suppress("MemberVisibilityCanBePrivate", "LeakingThis")
abstract class Command(val javaPlugin: JavaPlugin,
                       val commandName: String,
                       val permission: String = "",
                       val permissionMessage: String = "",
                       val usage: String = "",
                       val minLength: Int = 0,
                       val maxLength: Int = 0,
                       tabCompleter: TabCompleter? = null) : CommandExecutor, ICommand {

    init {
        val command: PluginCommand? = javaPlugin.getCommand(commandName)
        if (command != null) {
            command.run {
                permission = null
                permissionMessage = ""
                this.tabCompleter = tabCompleter
            }
            command.executor = this
        }
    }

    override fun onCommand(sender: CommandSender, command: org.bukkit.command.Command, s: String, args: Array<String>?): Boolean {
        hasPermission(sender) {
            if (args == null || args.isEmpty())
                perform(sender, emptyArray())
            else if (args.size < minLength || args.size > maxLength)
                sendUseMessage(sender)
            else perform(sender, args)
        }
        return true
    }

    override fun hasPermission(target: CommandSender, lambda: () -> Unit) = hasPermission(target, permission, lambda)

    override fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit) {
        if (hasPermission(target, permission)) lambda()
        if (permissionMessage == "")
            target.sendMessage("${ChatColor.RED}I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.")
        else if (permissionMessage.isNotEmpty())
            for (line in permissionMessage.replace("<permission>", permission).split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) target.sendMessage(line)
    }

    override fun hasPermission(target: CommandSender): Boolean = hasPermission(target, permission)

    override fun hasPermission(target: CommandSender, permission: String): Boolean {
        if (permission.isEmpty()) return true
        for (p in permission.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            if (target.hasPermission(p)) return true
        return false
    }

    override fun isPlayer(sender: CommandSender, lambda: (Player) -> Unit) = if (sender is Player) lambda(sender) else sender.sendMessage("Der Command ist nur für Spieler")

    override fun getTarget(sender: CommandSender, player: Player?, lambda: (Player) -> Unit) = if (player != null) lambda(player) else sender.sendMessage("§cDer Spieler ist nicht Online")

    override fun sendUseMessage(sender: CommandSender) = sender.sendMessage("§7Nutze: §8/$commandName §7$usage")
}
