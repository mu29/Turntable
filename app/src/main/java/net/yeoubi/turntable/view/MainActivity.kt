package net.yeoubi.turntable.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import net.yeoubi.turntable.R
import net.yeoubi.turntable.databinding.ActivityMainBinding
import net.yeoubi.turntable.utils.DimensionUtils
import net.yeoubi.turntable.extensions.updateMargins
import net.yeoubi.turntable.view.adapter.ItemChangeListener
import net.yeoubi.turntable.view.adapter.RecyclerAdapter
import net.yeoubi.turntable.view.common.AttachedView
import net.yeoubi.turntable.view.common.ViewModelActivity
import net.yeoubi.turntable.viewmodel.MainViewModel
import net.yeoubi.turntable.viewmodel.common.ViewModel
import net.yeoubi.turntable.viewmodel.item.MusicItemViewModel


class MainActivity : ViewModelActivity(), AppBarLayout.OnOffsetChangedListener {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    private lateinit var musicAdapter: RecyclerAdapter

    override fun createViewModel(context: Context, activity: AttachedView): ViewModel? {
        viewModel = MainViewModel(context, activity)
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        setSupportActionBar(toolbar)

        setup()
        viewModel.search("고백")
    }

    override fun onOffsetChanged(bar: AppBarLayout?, offset: Int) {
        val dimension = DimensionUtils.context(this)
        content_view.updateMargins(8, (-32 - dimension.toDp(offset) / (144 / 32)).toInt(), 8, 0)
    }

    private fun setup() {
        setRecyclerView()
        app_bar.addOnOffsetChangedListener(this)
    }

    private fun setRecyclerView() {
        musicAdapter = RecyclerAdapter(this)
        binding.musicList.apply {
            adapter = musicAdapter
            setHasFixedSize(true)
        }
        viewModel.musics.addOnListChangedCallback(ItemChangeListener {
            musicAdapter.reset(it.map(::MusicItemViewModel), R.layout.item_music)
        })
    }
}
