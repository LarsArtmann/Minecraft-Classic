/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.lartyhd.spigot.classic.shopwarps.commands

import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.03.2018 03:41.
 * Last edit 01.05.2018
 */
@Suppress("MemberVisibilityCanBePrivate", "LeakingThis")
abstract class Command(val javaPlugin: JavaPlugin,
                       val commandName: String,
                       val permission: String = "",
                       val usage: String = "",
                       val minLength: Int = 0,
                       val maxLength: Int = 0,
                       tabCompleter: TabCompleter? = null) : CommandExecutor {

    init {
        val command: PluginCommand? = javaPlugin.getCommand(commandName)
        if (command != null) {
            command.permission = permission
            command.tabCompleter = tabCompleter
            command.executor = this
        }
    }

    override fun onCommand(sender: CommandSender, command: org.bukkit.command.Command, s: String, args: Array<String>?): Boolean {
        if (args == null) perform(sender, emptyArray()) else if (args.size < minLength || args.size > maxLength) sendUseMessage(sender) else perform(sender, args)
        return true
    }

    abstract fun perform(sender: CommandSender, args: Array<String>)


    fun isPlayer(sender: CommandSender, lambda: (Player) -> Unit) = if (sender is Player) lambda(sender) else sender.sendMessage("Der Command ist nur für Spieler")

    fun getTarget(sender: CommandSender, player: Player?, lambda: (Player) -> Unit) = if (player != null) lambda(player) else sender.sendMessage("§cDer Spieler ist nicht Online")

    fun getTarget(sender: CommandSender, uuid: UUID, lambda: (Player) -> Unit) = getTarget(sender, Bukkit.getPlayer(uuid), lambda)

    fun getTarget(sender: CommandSender, name: String, lambda: (Player) -> Unit) = getTarget(sender, Bukkit.getPlayer(name), lambda)

    fun sendUseMessage(sender: CommandSender) = sender.sendMessage("§7Nutze: §8/$commandName §7$usage")
}
