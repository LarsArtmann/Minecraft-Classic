/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.commands

import de.lartyhd.spigot.classic.shopwarps.inventory.WarpsInventory
import de.lartyhd.spigot.classic.shopwarps.utils.Messages
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
        if (args.isEmpty()) WarpsInventory.openInventory(it)
        else try {
            if (args.size == 2 && args[0].equals("delete", true))
                hasPermission(sender, "shops.admin.delete") {
                    WarpsInventory.removeAndUpdateWarps(UUID.fromString(args[1]))
                    sender.sendMessage("${Messages.PREFIX}§bDu hast alle Shop von §9${args[1]} §bgelöscht")
                }
            else sendUseMessage(sender)
        } catch (ex: Throwable) {
            val message = "Es ist ein Fehler beim löschen der Warps von ${args[1]} aufgetreten"
            sender.sendMessage("§4$message §c(mehr Infos in der Console)§4.")
            System.err.println("$message:")
            ex.printStackTrace()
        }
    }

}