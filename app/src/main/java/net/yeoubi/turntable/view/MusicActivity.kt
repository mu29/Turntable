package net.yeoubi.turntable.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.Observable
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
import android.support.v7.widget.LinearLayoutManager
import net.yeoubi.turntable.common.constants.Extras
import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.view.adapter.ItemChangeListener
import net.yeoubi.turntable.view.adapter.RecyclerAdapter
import net.yeoubi.turntable.view.helper.OnPropertyChangedCallback
import net.yeoubi.turntable.view.helper.YoutubePlayerStateChangeListener
import net.yeoubi.turntable.viewmodel.item.ReserveItemViewModel

class MusicActivity : ViewModelActivity(), YouTubePlayer.OnInitializedListener, SearchFragment.OnReserveListener {

    lateinit var viewModel: MusicViewModel
    lateinit var binding: ActivityMusicBinding
    lateinit var videoView: YouTubePlayerSupportFragment
    lateinit var reserveAdapter: RecyclerAdapter

    override fun createViewModel(context: Context, activity: AttachedView): ViewModel? {
        viewModel = MusicViewModel(context, activity)
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_music)
        binding.viewModel = viewModel

        setup()
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
        player.setPlayerStateChangeListener(YoutubePlayerStateChangeListener(
            onEnd = { viewModel.next() },
            onError = { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
        ))

        viewModel.musicId.addOnPropertyChangedCallback(OnPropertyChangedCallback {
            val musicId = viewModel.musicId.get()
            if (musicId.isNullOrEmpty()) {
                viewModel.next()
            } else {
                player.loadVideo(musicId)
            }
        })

        viewModel.next()
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, errorReason: YouTubeInitializationResult) {
        Toast.makeText(this, errorReason.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onReserve() {
        viewModel.load()
    }

    private fun setup() {
        setVideoView()
        setSearchView()
        setRecyclerView()
    }

    private fun setVideoView() {
        videoView = supportFragmentManager.findFragmentById(R.id.view_video) as YouTubePlayerSupportFragment
        videoView.initialize(BuildConfig.YOUTUBE_API_KEY, this)
    }

    private fun setSearchView() {
        val searchView = supportFragmentManager.findFragmentById(R.id.view_search) as SearchFragment
        searchView.onReserveListener = this
    }

    private fun setRecyclerView() {
        reserveAdapter = RecyclerAdapter(this)

        binding.listReserve.apply {
            adapter = reserveAdapter
            layoutManager = LinearLayoutManager(this@MusicActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.reserveList.addOnListChangedCallback(ItemChangeListener {
            reserveAdapter.reset(
                it.map(::ReserveItemViewModel), R.layout.item_reserve,
                Extras.ON_CLICK, { item: Music -> viewModel.remove(item) }
            )
        })
    }
}
