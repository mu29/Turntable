package net.yeoubi.turntable.data.repository

import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.data.source.ReserveDataSource
import net.yeoubi.turntable.data.source.local.ReserveLocalDataSource
import javax.inject.Inject

/**
 * InJung Chung
 */

class ReserveRepository @Inject constructor() : ReserveDataSource {

    @Inject
    lateinit var localDataSource: ReserveLocalDataSource

    override fun push(music: Music) {
        localDataSource.push(music)
    }

    override fun pop(): Music? {
        return localDataSource.pop()
    }

    override fun remove(item: Music) {
        return localDataSource.remove(item)
    }

    override fun all(): List<Music> {
        return localDataSource.all()
    }
}
