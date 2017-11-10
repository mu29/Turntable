package net.yeoubi.turntable.data.source.remote

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import net.yeoubi.turntable.BuildConfig
import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.data.MusicList
import net.yeoubi.turntable.data.service.MusicService
import net.yeoubi.turntable.data.source.MusicDataSource
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * InJung Chung
 */

class MusicRemoteDataSource @Inject constructor(retrofit: Retrofit) : MusicDataSource {

    private val service = retrofit.create(MusicService::class.java)

    override fun search(query: String): Observable<MusicList> {
        return Observable.zip(
            service.search(query, "snippet", BuildConfig.YOUTUBE_API_KEY, BuildConfig.KY_CHANNEL_ID, 50),
            service.search(query, "snippet", BuildConfig.YOUTUBE_API_KEY, BuildConfig.TJ_CHANNEL_ID, 50),
            BiFunction { kyResult, tjResult ->
                val musics = arrayListOf<Music>()
                musics.addAll(tjResult.items)
                musics.addAll(kyResult.items)
                MusicList(musics)
            }
        )
    }

    override fun recent(): Observable<MusicList> {
        return service
            .recent("snippet", BuildConfig.YOUTUBE_API_KEY, BuildConfig.RECENT_PLAYLIST, 50)
            .map { MusicList(it.items.map { Music(it.info.identifier, it.info) }) }
    }
}
