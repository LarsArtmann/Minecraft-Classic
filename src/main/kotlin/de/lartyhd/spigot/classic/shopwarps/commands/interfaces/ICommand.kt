/*
 * © Copyright - SegdoMedia | Segdo aka. Dominik Milenko & Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.commands.interfaces

import de.lartyhd.spigot.classic.shopwarps.api.interfaces.permissions.tester.classvalues.DefaultClassValuesPermissionsTester
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.05.2018 09:41.
 * Last edit 22.05.2018
 */
interface ICommand : CommandExecutor, DefaultClassValuesPermissionsTester {

    override fun onCommand(sender: CommandSender, command: org.bukkit.command.Command, s: String, args: Array<String>?): Boolean

    fun perform(sender: CommandSender, args: Array<String>)

    fun isPlayer(sender: CommandSender, lambda: (Player) -> Unit)

    fun getTarget(sender: CommandSender, player: Player?, lambda: (Player) -> Unit)

    fun getTarget(sender: CommandSender, uuid: UUID, lambda: (Player) -> Unit) = getTarget(sender, Bukkit.getPlayer(uuid), lambda)

    fun getTarget(sender: CommandSender, name: String, lambda: (Player) -> Unit) = getTarget(sender, Bukkit.getPlayer(name), lambda)

    fun sendUseMessage(sender: CommandSender)

}