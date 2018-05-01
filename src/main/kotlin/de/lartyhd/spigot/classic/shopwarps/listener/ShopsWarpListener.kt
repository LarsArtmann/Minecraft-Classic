/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.listener

import de.lartyhd.spigot.classic.shopwarps.inventory.WarpsInventory
import de.lartyhd.spigot.classic.shopwarps.warp.NullWarp
import de.lartyhd.spigot.classic.shopwarps.warp.SimpleWarp
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 11:42.
 * Last edit 01.05.2018
 */
class ShopsWarpListener(javaPlugin: JavaPlugin) : Listener(javaPlugin) {

    private val config: MutableMap<Player, NullWarp> = HashMap()

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        if (event.inventory == null
                || event.inventory.title == null
                || event.inventory.title != "§9Shops") return
        cancel(event)
        val humanEntity = event.whoClicked
        if (event.slot == 49) {
            humanEntity.sendMessage("§eMit \"CANCEL\" kanst du immer abbrechen")
            if (config[humanEntity as Player] != null) {
                humanEntity.sendMessage("§cDu bist schon am erstellen eines Shops")
                humanEntity.closeInventory()
                return
            }
            config[humanEntity] = NullWarp(humanEntity.uniqueId, humanEntity.location)
            humanEntity.sendMessage("§aGib dein Namen des Shops ein:")
            humanEntity.closeInventory()
            return
        }
        if (event.slot < 9 || event.slot > 44
                || event.currentItem == null
                || event.currentItem.itemMeta == null
                || event.currentItem.itemMeta.displayName == null) return
        val warp = WarpsInventory.getWarp(event.currentItem.itemMeta.displayName.substring(2))
        if (event.currentItem != warp?.getItem()) return
        humanEntity.teleport(warp?.location)
    }

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        val player = event.player
        if (!config.contains(player)) return
        cancel(event)
        val createWarp: NullWarp = config[player]!!
        when {
            event.message == "CANCEL" -> {
                config.remove(player)
                player.sendMessage("§4ShopWarp erstellen / überschreiben abgebrochen")
            }
            createWarp.name == null -> {
                createWarp.name = event.message
                player.sendMessage("§aDer Name wurde gesetzt.")
                player.sendMessage("§aGib die ID des DisplayBlocks ein:")
            }
            createWarp.material == null -> {
                try {
                    val material = Material.getMaterial(event.message.toInt())
                    createWarp.material = if (material == Material.AIR) Material.STONE else material
                    player.sendMessage("§aDas Material wurde auf ${createWarp.material} gesetzt")
                    player.sendMessage("§aJetzt gebe bitte eine Beschreibung deines Shops ab")
                    player.sendMessage("§aMit jeder Nachricht die du jetzt ein gibt fügst du eine weitere Line hinzu")
                    player.sendMessage("§aUm den Shop jetzt zu erstellen einfach \"FINISH\" in den Chat schreiben")
                    player.sendMessage("§cAchtung: \"FINISH\" muss in CAPS geschrieben werden und du kannst Colorcodes nutzen (mit &):")
                } catch (ex: ClassCastException) {
                    player.sendMessage("§cNur Zahlen! Noch mal:")
                }
            }
            event.message == "FINISH" -> {
//                WarpsInventory.remove(createWarp.uuid)
                for (i in 0 until createWarp.lore.size) {
                    createWarp.lore[i] = createWarp.lore[i].replace('&', '§')
                }
                WarpsInventory.add(SimpleWarp(createWarp.uuid, createWarp.location!!, createWarp.material!!, createWarp.lore, createWarp.name!!))
                config.remove(player)
                WarpsInventory.updateWarps()
                player.sendMessage("§bDein ShopWarp wurde erstellt")
            }
            else -> {
                createWarp.lore.add(event.message)
                player.sendMessage("§aDeine Line wurde gespeichert.")
            }
        }
    }
}