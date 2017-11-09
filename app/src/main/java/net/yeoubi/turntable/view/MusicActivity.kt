package net.yeoubi.turntable.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import net.yeoubi.turntable.R
import net.yeoubi.turntable.databinding.ActivityMusicBinding
import net.yeoubi.turntable.view.common.AttachedView
import net.yeoubi.turntable.view.common.ViewModelActivity
import net.yeoubi.turntable.viewmodel.MusicViewModel
import net.yeoubi.turntable.viewmodel.common.ViewModel
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import net.yeoubi.turntable.BuildConfig


class MusicActivity : ViewModelActivity(), YouTubePlayer.OnInitializedListener {

    lateinit var viewModel: MusicViewModel
    lateinit var binding: ActivityMusicBinding
    lateinit var videoView: YouTubePlayerSupportFragment

    override fun createViewModel(context: Context, activity: AttachedView): ViewModel? {
        viewModel = MusicViewModel(context, activity)
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_music)
        binding.viewModel = viewModel

        viewModel.init()
        setup()
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
        if (!wasRestored) {
            player.cueVideo(viewModel.musicId.get())
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, errorReason: YouTubeInitializationResult) {
        Toast.makeText(this, errorReason.toString(), Toast.LENGTH_LONG).show()
    }

    private fun setup() {
        videoView = supportFragmentManager.findFragmentById(R.id.view_video) as YouTubePlayerSupportFragment
        videoView.initialize(BuildConfig.YOUTUBE_API_KEY, this)
    }
}
