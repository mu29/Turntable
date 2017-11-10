package net.yeoubi.turntable.data.service

import io.reactivex.Observable
import net.yeoubi.turntable.data.MusicList
import net.yeoubi.turntable.data.RecentMusicList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * InJung Chung
 */


interface MusicService {

    @GET("/youtube/v3/search")
    fun search(
        @Query("q") query: String,
        @Query("part") type: String,
        @Query("key") key: String,
        @Query("channelId") channel: String,
        @Query("maxResults") count: Int
    ): Observable<MusicList>

    @GET("/youtube/v3/playlistItems")
    fun recent(
        @Query("part") type: String,
        @Query("key") key: String,
        @Query("playlistId") playlist: String,
        @Query("maxResults") count: Int
    ): Observable<RecentMusicList>
}
