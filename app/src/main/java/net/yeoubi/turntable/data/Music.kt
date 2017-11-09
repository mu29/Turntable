package net.yeoubi.turntable.data

import com.google.gson.annotations.SerializedName

/**
 * InJung Chung
 */

class Music {
    @SerializedName("id") lateinit var identifier: Identifier
    @SerializedName("snippet") lateinit var info: Info

    val valid get() = info.title.matches(KY_REGEX)
    val artist get() = KY_REGEX.matchEntire(info.title)?.groups?.get(1)?.value
    val title get() = KY_REGEX.matchEntire(info.title)?.groups?.get(2)?.value
    val index get() = String.format("%05d", KY_REGEX.matchEntire(info.title)?.groups?.get(3)?.value?.toInt() ?: 0)

    companion object {
        val KY_REGEX = Regex("""\[KY 금영노래방\] (.*) - (.*) \(KY Karaoke No.KY(.*)\)""")
    }
}
