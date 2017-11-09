package net.yeoubi.turntable.data.source.local

import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.data.source.ReserveDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * InJung Chung
 */

@Singleton
class ReserveLocalDataSource @Inject constructor() : ReserveDataSource {

    private val musics: MutableList<Music> = arrayListOf()

    override fun push(music: Music) {
        musics.add(music)
    }

    override fun pop(): Music? {
        if (musics.size > 0) {
            val music = musics.first()
            musics.removeAt(0)
            return music
        }

        return null
    }

    override fun remove(item: Music) {
        if (musics.contains(item)) {
            musics.remove(item)
        }
    }

    override fun all(): List<Music> {
        return musics
    }
}
