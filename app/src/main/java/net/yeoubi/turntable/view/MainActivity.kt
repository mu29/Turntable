package net.yeoubi.turntable.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import net.yeoubi.turntable.R
import net.yeoubi.turntable.databinding.ActivityMainBinding
import net.yeoubi.turntable.common.utils.DimensionUtils
import net.yeoubi.turntable.common.extensions.updateMargins
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
        binding.viewContent.updateMargins(8, (-32 - dimension.toDp(offset) / (136 / 32f)).toInt(), 8, 0)
        binding.viewTitle.alpha = 1 + dimension.toDp(offset) / 45f
        binding.viewShadow.alpha = 1 + dimension.toDp(offset) / 136f
    }

    private fun setup() {
        setRecyclerView()
        binding.appBar.addOnOffsetChangedListener(this)
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
