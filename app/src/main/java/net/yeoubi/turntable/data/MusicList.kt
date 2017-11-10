package net.yeoubi.turntable.data

import com.google.gson.annotations.SerializedName

/**
 * InJung Chung
 */

class MusicList {
    @SerializedName("items") lateinit var items: List<Music>

    constructor()

    constructor(items: List<Music>) {
        this.items = items
    }
}
