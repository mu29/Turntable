package net.yeoubi.turntable.view.helper

import com.google.android.youtube.player.YouTubePlayer

/**
 * InJung Chung
 */

class YoutubePlayerStateChangeListener(
    private val onEnd: () -> Unit,
    private val onError: (String) -> Unit
) : YouTubePlayer.PlayerStateChangeListener {
    override fun onAdStarted() {}

    override fun onLoading() {}

    override fun onVideoStarted() {}

    override fun onLoaded(p0: String?) {}

    override fun onVideoEnded() {
        onEnd()
    }

    override fun onError(errorReason: YouTubePlayer.ErrorReason?) {
        onError(errorReason.toString())
    }
}
