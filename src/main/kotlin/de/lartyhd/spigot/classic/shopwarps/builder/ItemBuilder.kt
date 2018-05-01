/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.lartyhd.spigot.classic.shopwarps.builder

import org.bukkit.Color
import org.bukkit.DyeColor
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.block.BlockState
import org.bukkit.block.banner.Pattern
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * Created by LartyHD on 17.01.2018  01:32.
 */
@Suppress("unused")
class ItemBuilder(private val itemStack: ItemStack) : Builder<ItemStack> {


    /*CREATE*/
    constructor(material: Material) : this(ItemStack(material))

    constructor(material: Material, amount: Int) : this(ItemStack(material, amount))

    constructor(material: Material, damage: Short) : this(ItemStack(material, 1, damage))

    constructor(material: Material, amount: Int, damage: Short) : this(ItemStack(material, amount, damage))
    /*CREATE*/

    /*ItemStack Setter*/
    fun setMaterial(material: Material): ItemBuilder {
        itemStack.type = material
        return this
    }

    fun setType(material: Material): ItemBuilder {
        itemStack.type = material
        return this
    }

    fun setAmount(amount: Int): ItemBuilder {
        itemStack.amount = amount
        return this
    }

    fun setDamage(damage: Short): ItemBuilder {
        itemStack.durability = damage
        return this
    }

    fun setDurability(durability: Short): ItemBuilder {
        itemStack.durability = durability
        return this
    }
    /*ItemStack Setter*/

    /*ItemStack Add*/
    fun addEnchantment(enchantment: Enchantment, level: Int): ItemBuilder {
        itemStack.addEnchantment(enchantment, level)
        return this
    }

    fun addEnchantments(enchantments: Map<Enchantment, Int>): ItemBuilder {
        itemStack.addEnchantments(enchantments)
        return this
    }

    fun addUnsafeEnchantment(enchantment: Enchantment, level: Int): ItemBuilder {
        itemStack.addUnsafeEnchantment(enchantment, level)
        return this
    }

    fun addUnsafeEnchantments(enchantments: Map<Enchantment, Int>): ItemBuilder {
        itemStack.addUnsafeEnchantments(enchantments)
        return this
    }

    fun addLore(lore: List<String>): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.lore.addAll(lore)
        itemMeta.lore = itemMeta.lore
        itemStack.itemMeta = itemMeta
        return this
    }

    fun addLore(i: Int, lore: List<String>): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.lore.addAll(i, lore)
        itemMeta.lore = itemMeta.lore
        itemStack.itemMeta = itemMeta
        return this
    }

    fun addLore(lore: String): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.lore.add(lore)
        itemMeta.lore = itemMeta.lore
        itemStack.itemMeta = itemMeta
        return this
    }

    fun addLore(i: Int, lore: String): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.lore.add(i, lore)
        itemMeta.lore = itemMeta.lore
        itemStack.itemMeta = itemMeta
        return this
    }
    /*ItemStack Add*/

    /*ItemStack Remove*/
    fun removeEnchantment(enchantment: Enchantment): ItemBuilder {
        itemStack.removeEnchantment(enchantment)
        return this
    }

    fun removeLore(lore: List<String>): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.lore.removeAll(lore)
        itemMeta.lore = itemMeta.lore
        itemStack.itemMeta = itemMeta
        return this
    }

    fun removeLore(i: Int): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.lore.removeAt(i)
        itemMeta.lore = itemMeta.lore
        itemStack.itemMeta = itemMeta
        return this
    }
    /*ItemStack Remove*/

    /*
	 *ItemMeta Setter
	 */
    fun setLore(lore: List<String>): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.lore = lore
        itemStack.itemMeta = itemMeta
        return this
    }

    fun setLore(lore: String): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.lore = listOf(lore)
        itemStack.itemMeta = itemMeta
        return this
    }

    fun setName(name: String): ItemBuilder {
        return setDisplayName(name)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun setDisplayName(displayName: String): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.displayName = displayName
        itemStack.itemMeta = itemMeta
        return this
    }

    fun setBreakable(): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.spigot().isUnbreakable = false
        itemStack.itemMeta = itemMeta
        return this
    }

    fun setUnbreakable(): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.spigot().isUnbreakable = true
        itemStack.itemMeta = itemMeta
        return this
    }

    fun setUnbreakable(unbreakable: Boolean): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.spigot().isUnbreakable = unbreakable
        itemStack.itemMeta = itemMeta
        return this
    }
    /*
	 *ItemMeta Setter
	 */

    /*
	 *ItemMeta Add
	 */
    fun addEnchant(enchantment: Enchantment, level: Int): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.addEnchant(enchantment, level, true)
        itemStack.itemMeta = itemMeta
        return this
    }

    fun addEnchant(enchantment: Enchantment, level: Int, ignoreLevelRestriction: Boolean): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction)
        itemStack.itemMeta = itemMeta
        return this
    }

    fun addItemFlags(vararg itemFlags: ItemFlag): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.addItemFlags(*itemFlags)
        itemStack.itemMeta = itemMeta
        return this
    }
    /*
	 *ItemMeta Add
	 */

    /*
	 *ItemMeta Remove
	 */
    fun removeEnchant(enchantment: Enchantment): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.removeEnchant(enchantment)
        itemStack.itemMeta = itemMeta
        return this
    }

    fun removeItemFlags(vararg itemFlags: ItemFlag): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.removeItemFlags(*itemFlags)
        itemStack.itemMeta = itemMeta
        return this
    }
    /*
	 *ItemMeta Remove
	 */

    /*
	 *ItemMeta Clear
	 */
    fun clearEnchants(): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.enchants.clear()
        itemStack.itemMeta = itemMeta
        return this
    }

    fun clearItemFlags(): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.itemFlags.clear()
        itemStack.itemMeta = itemMeta
        return this
    }
    /*
	 *ItemMeta Clear
	 */

    /*
	 *ItemMeta Hide
	 */
    fun hideItemFlags(): ItemBuilder {
        val itemMeta = itemStack.itemMeta
        itemMeta.addItemFlags(*ItemFlag.values())
        itemStack.itemMeta = itemMeta
        return this
    }
    /*
	 *ItemMeta Hide
	 */

    /*
	 *BannerMeta Setter
	 */
    fun setBaseColor(dyeColor: DyeColor): ItemBuilder {
        try {
            val bannerMeta = itemStack.itemMeta as BannerMeta
            bannerMeta.baseColor = dyeColor
            itemStack.itemMeta = bannerMeta
        } catch (ignored: ClassCastException) {
        }

        return this
    }

    fun setPatterns(patterns: List<Pattern>): ItemBuilder {
        try {
            val bannerMeta = itemStack.itemMeta as BannerMeta
            bannerMeta.patterns = patterns
            itemStack.itemMeta = bannerMeta
        } catch (ignored: ClassCastException) {
        }

        return this
    }

    fun setPattern(i: Int, pattern: Pattern): ItemBuilder {
        try {
            val bannerMeta = itemStack.itemMeta as BannerMeta
            bannerMeta.setPattern(i, pattern)
            itemStack.itemMeta = bannerMeta
        } catch (ignored: ClassCastException) {
        }

        return this
    }
    /*
	 *BannerMeta Setter
	 */

    /*
	 *BannerMeta Add
	 */
    fun addPattern(pattern: Pattern): ItemBuilder {
        try {
            val bannerMeta = itemStack.itemMeta as BannerMeta
            bannerMeta.addPattern(pattern)
            itemStack.itemMeta = bannerMeta
        } catch (ignored: ClassCastException) {
        }

        return this
    }
    /*
	 *BannerMeta Add
	 */

    /*
	 *BannerMeta Remove
	 */
    fun removePattern(i: Int): ItemBuilder {
        try {
            val bannerMeta = itemStack.itemMeta as BannerMeta
            bannerMeta.removePattern(i)
            itemStack.itemMeta = bannerMeta
        } catch (ignored: ClassCastException) {
        }

        return this
    }
    /*
	 *BannerMeta Remove
	 */

    /*
	 *BlockStateMeta Setter
	 */
    fun setBlockState(blockState: BlockState): ItemBuilder {
        try {
            val blockStateMeta = itemStack.itemMeta as BlockStateMeta
            blockStateMeta.blockState = blockState
            itemStack.itemMeta = blockStateMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *BlockStateMeta Setter
	 */

    /*
	 *BookMeta Setter
	 */
    fun setTitle(title: String): ItemBuilder {
        try {
            val bookMeta = itemStack.itemMeta as BookMeta
            bookMeta.title = title
            itemStack.itemMeta = bookMeta
        } catch (ignored: ClassCastException) {
        }

        return this
    }

    fun setAuthor(author: String): ItemBuilder {
        try {
            val bookMeta = itemStack.itemMeta as BookMeta
            bookMeta.author = author
            itemStack.itemMeta = bookMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }

    fun setPage(i: Int, page: String): ItemBuilder {
        try {
            val bookMeta = itemStack.itemMeta as BookMeta
            bookMeta.setPage(i, page)
            itemStack.itemMeta = bookMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }

    fun setPages(pages: List<String>): ItemBuilder {
        try {
            val bookMeta = itemStack.itemMeta as BookMeta
            bookMeta.pages = pages
            itemStack.itemMeta = bookMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }

    fun setPages(vararg pages: String): ItemBuilder {
        try {
            val bookMeta = itemStack.itemMeta as BookMeta
            bookMeta.setPages(*pages)
            itemStack.itemMeta = bookMeta
        } catch (ignored: ClassCastException) {
        }

        return this
    }
    /*
	 *BookMeta Setter
	 */

    /*
	 *BookMeta Add
	 */
    fun addPage(vararg page: String): ItemBuilder {
        try {
            val bookMeta = itemStack.itemMeta as BookMeta
            bookMeta.addPage(*page)
            itemStack.itemMeta = bookMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *BookMeta Add
	 */

    /*
	 *FireworkEffectMeta Setter
	 */
    fun setFireworkEffectMetaEffect(fireworkEffect: FireworkEffect): ItemBuilder {
        try {
            val fireworkEffectMeta = itemStack.itemMeta as FireworkEffectMeta
            fireworkEffectMeta.effect = fireworkEffect
            itemStack.itemMeta = fireworkEffectMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *FireworkEffectMeta Setter
	 */

    /*
	 *FireworkMeta Setter
	 */
    fun setFireworkMetaEffect(fireworkEffect: FireworkEffect): ItemBuilder {
        try {
            val fireworkMeta = itemStack.itemMeta as FireworkMeta
            fireworkMeta.addEffect(fireworkEffect)
            itemStack.itemMeta = fireworkMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }

    fun setFireworkMetaEffect(vararg fireworkEffect: FireworkEffect): ItemBuilder {
        try {
            val fireworkMeta = itemStack.itemMeta as FireworkMeta
            fireworkMeta.addEffects(*fireworkEffect)
            itemStack.itemMeta = fireworkMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }

    fun setFireworkMetaEffect(fireworkEffect: Iterable<FireworkEffect>): ItemBuilder {
        try {
            val fireworkMeta = itemStack.itemMeta as FireworkMeta
            fireworkMeta.addEffects(fireworkEffect)
            itemStack.itemMeta = fireworkMeta
        } catch (ignored: ClassCastException) {
        }

        return this
    }

    fun setPower(power: Int): ItemBuilder {
        try {
            val fireworkMeta = itemStack.itemMeta as FireworkMeta
            fireworkMeta.power = power
            itemStack.itemMeta = fireworkMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *FireworkMeta Setter
	 */

    /*
	 *FireworkMeta Remove
	 */
    fun removeEffect(effect: Int): ItemBuilder {
        try {
            val fireworkMeta = itemStack.itemMeta as FireworkMeta
            fireworkMeta.removeEffect(effect)
            itemStack.itemMeta = fireworkMeta
        } catch (ignored: ClassCastException) {
        }

        return this
    }
    /*
	 *FireworkMeta Remove
	 */

    /*
	 *FireworkMeta Clear
	 */
    fun clearEffects(): ItemBuilder {
        try {
            val fireworkMeta = itemStack.itemMeta as FireworkMeta
            fireworkMeta.clearEffects()
            itemStack.itemMeta = fireworkMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *FireworkMeta Clear
	 */

    /*
	 *LeatherArmorMeta Setter
	 */
    fun setColor(color: Color): ItemBuilder {
        try {
            val leatherArmorMeta = itemStack.itemMeta as LeatherArmorMeta
            leatherArmorMeta.color = color
            itemStack.itemMeta = leatherArmorMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *LeatherArmorMeta Setter
	 */

    /*
	 *MapMeta Setter
	 */
    fun setScaling(value: Boolean): ItemBuilder {
        try {
            val mapMeta = itemStack.itemMeta as MapMeta
            mapMeta.isScaling = value
            itemStack.itemMeta = mapMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *MapMeta Setter
	 */

    /*
	 *PotionMeta Setter
	 */
    fun setMainEffect(potionEffectType: PotionEffectType): ItemBuilder {
        try {
            val potionMeta = itemStack.itemMeta as PotionMeta
            potionMeta.setMainEffect(potionEffectType)
            itemStack.itemMeta = potionMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *PotionMeta Setter
	 */

    /*
	 *PotionMeta Add
	 */
    fun addCustomEffect(potionEffect: PotionEffect, overwrite: Boolean): ItemBuilder {
        try {
            val potionMeta = itemStack.itemMeta as PotionMeta
            potionMeta.addCustomEffect(potionEffect, overwrite)
            itemStack.itemMeta = potionMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *PotionMeta Add
	 */

    /*
	 *PotionMeta Remove
	 */
    fun removeCustomEffect(potionEffectType: PotionEffectType): ItemBuilder {
        try {
            val potionMeta = itemStack.itemMeta as PotionMeta
            potionMeta.removeCustomEffect(potionEffectType)
            itemStack.itemMeta = potionMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *PotionMeta Remove
	 */

    /*
	 *PotionMeta Clear
	 */
    fun clearCustomEffects(): ItemBuilder {
        try {
            val potionMeta = itemStack.itemMeta as PotionMeta
            potionMeta.clearCustomEffects()
            itemStack.itemMeta = potionMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *PotionMeta Clear
	 */

    /*
	 *SkullMeta Setter
	 */
    fun setOwner(owner: String): ItemBuilder {
        try {
            val skullMeta = itemStack.itemMeta as SkullMeta
            skullMeta.owner = owner
            itemStack.itemMeta = skullMeta
        } catch (ignored: ClassCastException) {
        }
        return this
    }
    /*
	 *SkullMeta Setter
	 */

    /*
	 *Extras
	 */
    fun clone(): ItemBuilder {
        return ItemBuilder(itemStack.clone())
    }

    override fun build(): ItemStack {
        return itemStack
    }
    /*
	 *Extras
	 */
}
