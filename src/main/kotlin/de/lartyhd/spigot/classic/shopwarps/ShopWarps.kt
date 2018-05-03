/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps

import de.lartyhd.spigot.classic.shopwarps.commands.ShopsCommand
import de.lartyhd.spigot.classic.shopwarps.config.ConfigManager
import de.lartyhd.spigot.classic.shopwarps.inventory.WarpsInventory
import de.lartyhd.spigot.classic.shopwarps.listener.ShopsWarpListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 07:56.
 * Last edit 01.05.2018
 */
class ShopWarps : JavaPlugin() {

    val configManager = ConfigManager(dataFolder, "shops.yml")

    override fun onEnable() {
        configManager.addWarps(WarpsInventory.warps)
        WarpsInventory.updateWarps()
        ShopsCommand(this)
        ShopsWarpListener(this)
    }

//    override fun onDisable() {
//        configManager.setWarps(WarpsInventory.warps)
//    }
}
