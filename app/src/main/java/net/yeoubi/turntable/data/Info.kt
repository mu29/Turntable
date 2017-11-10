package net.yeoubi.turntable.data

import com.google.gson.annotations.SerializedName

/**
 * InJung Chung
 */

class Info(
    val title: String,
    val description: String
) {
    @SerializedName("resourceId") lateinit var identifier: Identifier
}
