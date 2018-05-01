/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.lartyhd.spigot.classic.shopwarps.listener

import org.bukkit.Bukkit
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by LartyHD on 19.02.2018  02:34.
 * Last edit 13.03.2018
 */
@Suppress("LeakingThis", "unused", "MemberVisibilityCanBePrivate")
open class Listener(val javaPlugin: JavaPlugin) : org.bukkit.event.Listener {

    init {
        init()
        register()
    }

    open fun init() {}

    fun register() {
        Bukkit.getPluginManager().registerEvents(this, this.javaPlugin)
    }

    fun unregister() {
        HandlerList.unregisterAll(this)
    }

    fun reload() {
        unregister()
        register()
    }

    protected fun cancel(cancellable: Cancellable) {
        cancel(cancellable, true)
    }

    protected fun cancel(cancellable: Cancellable, boolean: Boolean) {
        cancellable.isCancelled = boolean
    }
}
