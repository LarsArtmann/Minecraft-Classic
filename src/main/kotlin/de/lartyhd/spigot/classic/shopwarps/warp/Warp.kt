/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.warp

import de.lartyhd.spigot.classic.shopwarps.builder.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 11:24.
 * Last edit 01.05.2018
 */
interface Warp {
	val uuid: UUID
	val location: Location?
	val material: Material?
	val lore: MutableList<String>
	val name: String?

//	fun getItem() = ItemBuilder(material!!).setLore(lore).setName("§9$name").build()

	fun getItemWithUUIDLore(): ItemStack {
		val item = ItemBuilder(material!!)
				.setLore(lore)
				.addLore("", "§eDieser Shop gehört: §a${Bukkit.getOfflinePlayer(uuid).name ?: "UNBEKANNT"}")
				.setName("§9$name")
				.hideItemFlags()
		if (Bukkit.getPlayer(uuid) != null)
			item.addEnchant(Enchantment.LUCK, 10, true)

		return item.build()
	}
}
