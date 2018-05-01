/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkblocks.dark.kotlin.builder

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 10.11.2017 01:52.
 * Last edit 01.04.2018
 */
interface Builder<out T> {
    fun build(): T
}
