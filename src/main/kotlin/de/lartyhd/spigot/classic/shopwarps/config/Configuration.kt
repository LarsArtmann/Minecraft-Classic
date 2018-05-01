/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.lartyhd.spigot.classic.shopwarps.config

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

@Suppress("MemberVisibilityCanBePrivate")
/**
 * Created by LartyHD on 02.08.2017  14:06.
 */
class Configuration(var file: File) : YamlConfiguration() {
    init {
        load(file)
    }
}