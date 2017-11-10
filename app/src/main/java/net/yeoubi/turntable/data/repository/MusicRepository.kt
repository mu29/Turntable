package net.yeoubi.turntable.data.repository

import io.reactivex.Observable
import net.yeoubi.turntable.data.MusicList
import net.yeoubi.turntable.data.source.MusicDataSource
import net.yeoubi.turntable.data.source.remote.MusicRemoteDataSource
import javax.inject.Inject

/**
 * InJung Chung
 */

class MusicRepository @Inject constructor() : MusicDataSource {

    @Inject
    lateinit var remoteDataSource: MusicRemoteDataSource

    override fun search(query: String): Observable<MusicList> {
        return remoteDataSource.search(query)
    }

    override fun recent(): Observable<MusicList> {
        return remoteDataSource.recent()
    }
}
