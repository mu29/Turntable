package net.yeoubi.turntable.data.source

import net.yeoubi.turntable.data.Music

/**
 * InJung Chung
 */

interface ReserveDataSource {

    fun push(music: Music)

    fun pop(): Music

    fun all(): List<Music>
}
