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
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.firebase.crash.FirebaseCrash
import net.yeoubi.turntable.common.constants.Extras
import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.view.adapter.ItemChangeListener
import net.yeoubi.turntable.view.adapter.RecyclerAdapter
import net.yeoubi.turntable.view.helper.OnPropertyChangedCallback
import net.yeoubi.turntable.view.helper.YoutubePlayerStateChangeListener
import net.yeoubi.turntable.viewmodel.item.ReserveItemViewModel
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.AdListener

class MusicActivity : ViewModelActivity(), YouTubePlayer.OnInitializedListener, SearchFragment.OnReserveListener {

    lateinit var viewModel: MusicViewModel
    lateinit var binding: ActivityMusicBinding

    private lateinit var videoView: YouTubePlayerSupportFragment
    private lateinit var reserveAdapter: RecyclerAdapter
    private lateinit var interstitialAd: InterstitialAd
    private var initialized = false

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
        initVideoCallbacks(player)
        if (!wasRestored)
            viewModel.next()
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, errorReason: YouTubeInitializationResult) {
        Toast.makeText(this, errorReason.toString(), Toast.LENGTH_LONG).show()
        FirebaseCrash.logcat(Log.ERROR, errorReason.name, errorReason.toString())
    }

    override fun onReserve() {
        viewModel.load()
    }

    private fun initVideoCallbacks(player: YouTubePlayer) {
        if (initialized)
            return

        initialized = true

        player.setPlayerStateChangeListener(YoutubePlayerStateChangeListener(
            onEnd = { viewModel.next() },
            onError = {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                FirebaseCrash.logcat(Log.ERROR, "StateChangeError", it)
            }
        ))

        viewModel.musicId.addOnPropertyChangedCallback(OnPropertyChangedCallback {
            val musicId = viewModel.musicId.get()
            if (musicId.isNullOrEmpty()) {
                viewModel.next()
            } else {
                player.loadVideo(musicId)
            }
        })
    }

    private fun setup() {
        setVideoView()
        setSearchView()
        setRecyclerView()
        initInterstitialAd()
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

    private fun initInterstitialAd() {
        interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = resources.getString(R.string.interstitial_id)
        interstitialAd.loadAd(AdRequest.Builder().build())
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                interstitialAd.loadAd(AdRequest.Builder().build())
            }
        }

        viewModel.shouldShowAd.addOnPropertyChangedCallback(OnPropertyChangedCallback {
            if (interstitialAd.isLoaded) {
                interstitialAd.show()
            }
        })
    }
}
