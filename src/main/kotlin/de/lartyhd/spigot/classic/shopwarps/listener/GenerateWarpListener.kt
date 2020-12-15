/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.listener

import de.lartyhd.spigot.classic.shopwarps.api.interfaces.permissions.tester.DefaultPermissionsTester
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2018 05:53.
 * Last edit 24.05.2018
 */
class GenerateWarpListener(javaPlugin: JavaPlugin) : Listener(javaPlugin), DefaultPermissionsTester {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val inventory = event.inventory
        val title = event.view.title
        if (!title.startsWith("§9Shops §8- §9")) return
//        val subtitle = title.substring("§9Shops §8- §9".length)
        val currentItem = event.currentItem ?: return
//        val itemMeta = currentItem.itemMeta ?: return
//        val displayName = itemMeta.displayName
        val humanEntity = event.whoClicked
        if (humanEntity.openInventory.topInventory != inventory) return
        cancel(event)
//        when (subtitle) {
//            "Main" -> if (displayName == "§9Einstellungen") {
//                val warp = WarpsInventory.getWarp(humanEntity.uniqueId)
//                if (warp == null) WarpsInventory.openCreate(humanEntity) else WarpsInventory.openSettings(humanEntity)
//            } else if (event.slot > 9 || event.slot < 44) {
//                val warp = WarpsInventory.getWarp(displayName.substring(2)) ?: return
//                if (currentItem == warp.getItem()) humanEntity.teleport(warp.location)
//            }
//            "Settings" -> {
//                when (displayName) {
//                    "§9Warp löschen" -> {
//                        if (hasPermission(humanEntity, "shopwarps.gui.delete.waps")) {
//
//                        } else {
//
//                        }
//                    }
//                    "§9Warp Einstellungen anpassen" -> {
//
//                    }
//                    "§9Warp neu erstellen" -> {
//
//                    }
//                }
//            }
//            "Create" -> if (displayName == "§9Neuen Warp erstellen") {
//                TODO()
//            }
//
//        }
        if (event.slot in 9..44 && currentItem.type != Material.AIR) humanEntity.closeInventory()
    }

}
















