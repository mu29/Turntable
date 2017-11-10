package net.yeoubi.turntable.data

import com.google.gson.annotations.SerializedName

/**
 * InJung Chung
 */

class RecentMusicList {
    @SerializedName("items") lateinit var items: List<RecentMusic>
}
