package net.yeoubi.turntable.data.source

import io.reactivex.Observable
import net.yeoubi.turntable.data.MusicList

/**
 * InJung Chung
 */

interface MusicDataSource {

    fun search(query: String): Observable<MusicList>

    fun recent(): Observable<MusicList>
}
