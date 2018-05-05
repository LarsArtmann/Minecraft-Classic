/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.commands

import de.lartyhd.spigot.classic.shopwarps.inventory.WarpsInventory
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 10:48.
 * Last edit 01.05.2018
 */
class ShopsCommand(javaPlugin: JavaPlugin) : Command(
        javaPlugin = javaPlugin,
        commandName = "Shops",
        permission = "shops.use",
        usage = "[delete <UUID>]",
        maxLength = 2
) {

    override fun perform(sender: CommandSender, args: Array<String>) = isPlayer(sender) {
        WarpsInventory.openInventory(it)
        try {
            if (it.hasPermission("shops.admin.delete") && args.size == 2) {
                WarpsInventory.remove(UUID.fromString(args[1]))
                WarpsInventory.updateWarps()
                sender.sendMessage("§4Du hast den Shop von ${args[1]}")
            } else sendUseMessage(sender)
        } catch (ex: Exception) {
            sender.sendMessage("§4Es ist ein Fehler aufgetreten")
        }
    }

}