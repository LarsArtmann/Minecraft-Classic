/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.lartyhd.spigot.classic.shopwarps.builder

import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

@Suppress("unused")
class InventoryBuilder(private val inventory: Inventory) : Builder<Inventory> {

    constructor(type: InventoryType) : this(null, type)

    constructor(slots: Int) : this(null, slots)

    constructor(type: InventoryType, name: String) : this(null, type, name)

    constructor(slots: Int, name: String) : this(null, slots, name)

    constructor(owner: InventoryHolder?, type: InventoryType) : this(Bukkit.createInventory(owner, type))

    constructor(owner: InventoryHolder?, type: InventoryType, name: String) : this(Bukkit.createInventory(owner, type, name))

    constructor(owner: InventoryHolder?, slots: Int) : this(Bukkit.createInventory(owner, slots))

    constructor(owner: InventoryHolder?, slots: Int, name: String) : this(Bukkit.createInventory(owner, slots, name))

    fun setItem(slot: Int, item: ItemStack): InventoryBuilder {
        this.inventory.setItem(slot, item)
        return this
    }

    fun addItem(item: ItemStack): InventoryBuilder {
        this.inventory.addItem(item)
        return this
    }

    fun fillWith(item: ItemStack): InventoryBuilder {
        for (i in 0 until this.inventory.size + 1) {
            if (this.inventory.getItem(i) != null) continue
            this.inventory.setItem(i, item)
        }
        return this
    }

    fun fillWith(item: ItemStack, start: Int, end: Int): InventoryBuilder {
        for (i in start until end + 1) {
            if (this.inventory.getItem(i) != null) continue
            this.inventory.setItem(i, item)
        }
        return this
    }

    override fun build(): Inventory {
        return this.inventory
    }
}
