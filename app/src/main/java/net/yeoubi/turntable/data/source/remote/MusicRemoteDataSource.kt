package net.yeoubi.turntable.data.source.remote

import io.reactivex.Observable
import net.yeoubi.turntable.BuildConfig
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
        return service.search(query, "snippet", BuildConfig.YOUTUBE_API_KEY, BuildConfig.CHANNEL_ID, 50)
    }
}
