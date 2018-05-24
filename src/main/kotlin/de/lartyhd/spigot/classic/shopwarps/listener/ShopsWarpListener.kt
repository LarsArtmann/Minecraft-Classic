/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.listener

import de.lartyhd.spigot.classic.shopwarps.inventory.WarpsInventory
import de.lartyhd.spigot.classic.shopwarps.warp.NullWarp
import de.lartyhd.spigot.classic.shopwarps.warp.SimpleWarp
import org.bukkit.Material
import org.bukkit.Material.*
import org.bukkit.command.CommandSender
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
    /* private val blockedMaterials = listOf(
             Material.AIR,
             Material.STATIONARY_WATER,
             Material.WATER,
             Material.STATIONARY_LAVA,
             Material.LAVA,
             Material.getMaterial(34),
             Material.FIRE,
             Material.REDSTONE_WIRE,
             Material.WHEAT,
             Material.BURNING_FURNACE,
             Material.SIGN_POST,
             Material.WALL_SIGN,
             Material.GLOWING_REDSTONE_ORE,
             Material.REDSTONE_LAMP_OFF,
             Material.PORTAL,
             Material.getMaterial(93),
             Material.getMaterial(94),
             Material.STANDING_BANNER,
             Material.DOUBLE_STONE_SLAB2
     )*/

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        if (event.inventory == null
                || event.inventory.title == null
                || event.inventory.title != "§9Shops") return
        cancel(event)
        val humanEntity = event.whoClicked
        if ((event.slot == 52 || event.slot == 46 || (event.slot in 9..44)) && event.currentItem != null && event.currentItem.type != Material.AIR) humanEntity.closeInventory()
        when {
            event.slot == 52 -> {
                humanEntity.sendMessage("§eMit \"CANCEL\" kanst du immer abbrechen")
                if (config[humanEntity as Player] != null) humanEntity.sendMessage("§cDu bist schon am erstellen eines Shops") else {
                    config[humanEntity] = NullWarp(humanEntity.uniqueId, humanEntity.location)
                    humanEntity.sendMessage("§eGib dein Namen des Shops ein:")
                }
            }
            event.slot == 46 -> {
                WarpsInventory.removeAndUpdateWarps(humanEntity.uniqueId)
                humanEntity.sendMessage("§bDein ShopWarp wurde gelöscht")
            }
            event.slot < 9 || event.slot > 44
                    || event.currentItem == null
                    || event.currentItem.itemMeta == null
                    || event.currentItem.itemMeta.displayName == null -> return
        }
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
                val warp = WarpsInventory.getWarp(event.message)
                if (warp != null && warp.uuid != player.uniqueId) {
                    player.sendMessage("§cDen Namen hat leider schon ein anderer Shop :(.")
                    return
                } else {
                    createWarp.name = event.message
                    player.run {
                        sendMessage("§aDer Name wurde gesetzt.")
                        sendMessage("§eGib die ID des DisplayBlocks ein:")
                    }
                }
            }
            createWarp.material == null -> {
                val material = getMaterialByID(event.message, player)
                if (material == null) player.sendMessage("§cEs ist ein Fehler aufgetreten. Bitte gebe noch mal die Material ID eingebe") else {
                    createWarp.material = material
                    player.run {
                        sendMessage("§aDas Material wurde auf ${createWarp.material} gesetzt.")
                        sendMessage("§eJetzt gebe bitte eine Beschreibung deines Shops ab.")
                        sendMessage("§eMit jeder Nachricht die du jetzt ein gibt fügst du eine weitere Line hinzu.")
                        sendMessage("§eUm den Shop jetzt zu erstellen einfach \"FINISH\" in den Chat schreiben.")
                        sendMessage("§6Achtung: \"FINISH\" muss in CAPS geschrieben werden und du kannst Colorcodes nutzen (mit &):")
                    }
                }
            }
            event.message == "FINISH" -> {
                addWarp(createWarp)
                config.remove(player)
                player.sendMessage("§bDein ShopWarp wurde erstellt")
            }
            else -> {
                createWarp.lore.add(event.message)
                player.sendMessage("§aDeine Line wurde gespeichert.")
            }
        }
    }

    private fun getMaterialByID(id: String, sender: CommandSender): Material? {
        return try {
//                    val split = event.message.split(":")
//                    var subID = 0
//                    if (split.size == 2) subID = split[1].toInt()
//                    var material = Material.getMaterial(split[0].toInt())
            val material = Material.getMaterial(id.toInt())
            if (material == null) {
                sender.sendMessage("§cUnerwarteter Fehler")
                return null
            }
            println(material)
            if (!isItem(material)) {
                sender.sendMessage("§cDieses Material ist kein Item :(")
                return null
            }
            /* for (blockedMaterial in blockedMaterials) {
                 if (blockedMaterial == material) {
                     sender.sendMessage("§cDieses Material ist verboten :(")
                     return null
                 }
             }*/
            material
        } catch (ex: NumberFormatException) {
            sender.sendMessage("§cNur Zahlen!"/* ID oder ID:SUB_ID*/)
            null
        }
    }

    private fun addWarp(warp: NullWarp) {
        for (i in 0 until warp.lore.size) warp.lore[i] = warp.lore[i].replace('&', '§')
        WarpsInventory.add(SimpleWarp(warp.uuid, warp.location!!, warp.material!!, warp.lore, warp.name!!))
    }

    /**
     * Stolen by Spigot
     * Checks if this Material is an obtainable item.
     *
     * @return true if this material is an item
     */
    fun isItem(material: Material) = when (material) {
        ACACIA_DOOR, BED_BLOCK, BEETROOT_BLOCK, BIRCH_DOOR, BREWING_STAND, BURNING_FURNACE, CAKE_BLOCK, CARROT, CAULDRON, COCOA, CROPS, DARK_OAK_DOOR, DAYLIGHT_DETECTOR_INVERTED, DIODE_BLOCK_OFF, DIODE_BLOCK_ON, DOUBLE_STEP, DOUBLE_STONE_SLAB2, ENDER_PORTAL, END_GATEWAY, FIRE, FLOWER_POT, FROSTED_ICE, GLOWING_REDSTONE_ORE, IRON_DOOR_BLOCK, JUNGLE_DOOR, LAVA, MELON_STEM, NETHER_WARTS, PISTON_EXTENSION, PISTON_MOVING_PIECE, PORTAL, POTATO, PUMPKIN_STEM, PURPUR_DOUBLE_SLAB, REDSTONE_COMPARATOR_OFF, REDSTONE_COMPARATOR_ON, REDSTONE_LAMP_ON, REDSTONE_TORCH_OFF, REDSTONE_WIRE, SIGN_POST, SKULL, SPRUCE_DOOR, STANDING_BANNER, STATIONARY_LAVA, STATIONARY_WATER, SUGAR_CANE_BLOCK, TRIPWIRE, WALL_BANNER, WALL_SIGN, WATER, WOODEN_DOOR, WOOD_DOUBLE_STEP -> false
        else -> true
    }
}