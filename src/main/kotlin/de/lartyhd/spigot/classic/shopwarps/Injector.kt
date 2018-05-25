/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps

import de.lartyhd.spigot.classic.shopwarps.commands.ShopsCommand
import de.lartyhd.spigot.classic.shopwarps.config.ConfigManager
import de.lartyhd.spigot.classic.shopwarps.inventory.WarpsInventory
import de.lartyhd.spigot.classic.shopwarps.listener.ShopsWarpListener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2018 01:19.
 * Last edit 24.05.2018
 */
object Injector {
    lateinit var configManager: ConfigManager

    fun init(javaPlugin: JavaPlugin) {
        initConfigManager(javaPlugin.dataFolder)
        addWarps()
        ShopsCommand(javaPlugin)
        ShopsWarpListener(javaPlugin)
//        GenerateWarpListener(javaPlugin)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun addWarps() {
        configManager.addWarps(WarpsInventory.warps)
        WarpsInventory.updateWarps()
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun isInitialized(lambda: () -> Unit): Boolean = try {
        lambda()
        true
    } catch (ex: UninitializedPropertyAccessException) {
        false
    }

    private fun initConfigManager(dataFolder: File) {
        if (!isInitialized { configManager }) setConfigManger(dataFolder)
    }

    private fun setConfigManger(dataFolder: File) {
        configManager = ConfigManager(dataFolder, "shops.yml")
    }
}