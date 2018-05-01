/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.commands

import de.lartyhd.spigot.classic.shopwarps.inventory.WarpsInventory
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 10:48.
 * Last edit 01.05.2018
 */
class ShopsCommand(javaPlugin: JavaPlugin) : Command(
        javaPlugin = javaPlugin,
        commandName = "Shops",
        permission = "shops.use"
) {

    override fun perform(sender: CommandSender, args: Array<String>) = isPlayer(sender) {
        WarpsInventory.openInventory(it)
    }

}