/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.injector

import de.lartyhd.spigot.classic.shopwarps.commands.ShopsCommand
import de.lartyhd.spigot.classic.shopwarps.config.ConfigManager
import de.lartyhd.spigot.classic.shopwarps.inventory.WarpsInventory
import de.lartyhd.spigot.classic.shopwarps.listener.ShopsWarpListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.05.2018 16:47.
 * Last edit 04.05.2018
 */
class JavaPluginInjector(private val javaPlugin: JavaPlugin) : Injector {

    val configManager = ConfigManager(javaPlugin.dataFolder, "shops.yml")

    override fun inject() {
        configManager.addWarps(WarpsInventory.warps)
        WarpsInventory.updateWarps()
        ShopsCommand(javaPlugin)
        ShopsWarpListener(javaPlugin)
    }

}