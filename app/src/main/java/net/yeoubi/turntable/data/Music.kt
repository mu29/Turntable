package net.yeoubi.turntable.data

import com.google.gson.annotations.SerializedName

/**
 * InJung Chung
 */

class Music {
    @SerializedName("id") lateinit var identifier: Identifier
    @SerializedName("snippet") lateinit var info: Info

    val id get() = identifier.videoId
    val valid get() = info.title.matches(KY_REGEX) || info.title.matches(TJ_REGEX)

    val type: Regex?
        get() {
            if (!valid)
                return null

            if (info.title.matches(KY_REGEX))
                return KY_REGEX
            else if (info.title.matches(TJ_REGEX))
                return TJ_REGEX

            return null
        }

    val artist: String
        get() {
            val artist = when (type) {

                KY_REGEX -> KY_REGEX.matchEntire(info.title)?.groups?.get(1)?.value
                TJ_REGEX -> TJ_REGEX.matchEntire(info.title.replace(Regex(""" \(.*\)"""), ""))?.groups?.get(2)?.value
                else -> null
            }
            return artist ?: ""
        }

    val title: String
        get() {
            val title = when (type) {
                KY_REGEX -> KY_REGEX.matchEntire(info.title)?.groups?.get(2)?.value
                TJ_REGEX -> TJ_REGEX.matchEntire(info.title.replace(Regex(""" \(.*\)"""), ""))?.groups?.get(1)?.value
                else -> null
            }
            return title ?: ""
        }

    val index: String
        get() {
            val index = when (type) {
                KY_REGEX -> String.format("KY%05d", KY_REGEX.matchEntire(info.title)?.groups?.get(3)?.value?.toInt() ?: 0)
                TJ_REGEX -> {
                    val result = TJ_INDEX_REGEX.find(info.description)
                    val number = result?.groupValues?.first()?.replace(Regex("""\D"""), "")
                    String.format("TJ%05d", number?.toIntOrNull() ?: 0)
                }
                else -> null
            }
            return index ?: ""
        }

    constructor()

    constructor(identifier: Identifier, info: Info) {
        this.identifier = identifier
        this.info = info
    }

    companion object {
        val KY_REGEX = Regex("""\[KY 금영노래방\] (.*) - (.*) \(KY Karaoke No.KY(.*)\)""")
        val TJ_REGEX = Regex("""\[TJ노래방\] (.*) - (.*) / TJ Karaoke""")
        val TJ_INDEX_REGEX = Regex("""(TJ노래방 곡번호 (\d*)|TJ 노래방 곡번호 (\d*)|TJ 노래방 곡번호\.(\d*)|TJ노래방 (\d*))""")
    }
}
