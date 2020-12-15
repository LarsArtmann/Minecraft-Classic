/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.lartyhd.spigot.classic.shopwarps.commands

import de.lartyhd.spigot.classic.shopwarps.commands.interfaces.ICommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.03.2018 03:41.
 * Last edit 24.05.2018
 */
@Suppress("MemberVisibilityCanBePrivate", "LeakingThis")
abstract class Command(val javaPlugin: JavaPlugin,
                       val commandName: String,
                       override val permission: String = "",
                       override val permissionMessage: String = "",
                       var usage: String = "",
                       val minLength: Int = 0,
                       val maxLength: Int = 0,
                       tabCompleter: TabCompleter? = null) : ICommand {

    var prefix = "§f[§b${javaPlugin.name}§f] §r"

    init {
        val command: PluginCommand? = javaPlugin.getCommand(commandName)
        if (command != null) {
            command.run {
                permission = null
                permissionMessage = ""
                this.tabCompleter = tabCompleter
            }
            command.javaClass.getDeclaredField("executor").apply {
                isAccessible = true
            }.set(command, this)
        }
        if (maxLength > 0) usage = if (minLength == 0) "|[help]|$usage" else "<help>|$usage"
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        hasPermission(sender) {
            when {
                args.isEmpty() -> perform(sender, emptyArray())
                args.size < minLength || args.size > maxLength || (maxLength > 0 && args[0].equals("help", true)) ->
                    sendUseMessage(sender)
                else -> perform(sender, args)
            }
        }
        return true
    }
//
//    override fun hasPermission(target: CommandSender, lambda: () -> Unit) = hasPermission(target, permission, lambda)
//
//    override fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit) {
//        when {
//            hasPermission(target, permission) -> lambda()
//            permissionMessage == "" -> target.sendMessage("${ChatColor.RED}I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.")
//            permissionMessage.isNotEmpty() -> for (line in permissionMessage.replace("<permission>", permission).split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) target.sendMessage(line)
//        }
//    }
//
//    override fun hasPermission(target: CommandSender): Boolean = hasPermission(target, permission)
//
//    override fun hasPermission(target: CommandSender, permission: String): Boolean {
//        if (permission.isEmpty()) return true
//        for (p in split(permission, ";"))
//            if (target.hasPermission(p)) return true
//        return false
//    }

    override fun isPlayer(sender: CommandSender, lambda: (Player) -> Unit) = if (sender is Player) lambda(sender) else sender.sendMessage("Der Command ist nur für Spieler")

    override fun getTarget(sender: CommandSender, player: Player?, lambda: (Player) -> Unit) = if (player != null) lambda(player) else sender.sendMessage("§cDer Spieler ist nicht Online")

    override fun sendUseMessage(sender: CommandSender) = if (split(usage, "|").isEmpty())
        sender.sendMessage("$prefix§7Nutze: §8/$commandName §7$usage")
    else {
        sender.sendMessage("$prefix§7Nutze:")
        for (usage in split(usage, "|")) {
            if (":" in usage) { //SOLL WIE "usage.contains(":")" SEIN
                val subCommand = split(usage, ":")
                if (hasPermission(sender, subCommand[1])) {
                    sendUseMessage(sender, subCommand[0])
                }
            } else sendUseMessage(sender, usage)
        }
    }

    private fun sendUseMessage(sender: CommandSender, usage: String) {
        sender.sendMessage("§7- §8/$commandName §7$usage")
    }
}
