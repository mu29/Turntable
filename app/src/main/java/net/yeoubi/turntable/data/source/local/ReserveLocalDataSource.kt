package net.yeoubi.turntable.data.source.local

import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.data.source.ReserveDataSource
import javax.inject.Inject

/**
 * InJung Chung
 */

class ReserveLocalDataSource @Inject constructor() : ReserveDataSource {

    private val musics: MutableList<Music> = arrayListOf()

    override fun push(music: Music) {
        musics.add(music)
    }

    override fun pop(): Music {
        val music = musics.first()
        musics.removeAt(0)
        return music
    }

    override fun all(): List<Music> {
        return musics
    }
}
