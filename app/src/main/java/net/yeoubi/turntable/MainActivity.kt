package net.yeoubi.turntable

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.yeoubi.turntable.utils.DimensionUtils
import net.yeoubi.turntable.extensions.updateMargins


class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private val musicAdapter = MusicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        app_bar.addOnOffsetChangedListener(this)

        setRecyclerView()
    }

    override fun onOffsetChanged(bar: AppBarLayout?, offset: Int) {
        val dimension = DimensionUtils.context(this)
        background_image.alpha = 1 - 0.8f * (-dimension.toDp(offset) / 184.0f)
        content_view.updateMargins(8, (-32 - dimension.toDp(offset) / (192 / 32)).toInt(), 8, 0)
    }

    private fun setRecyclerView() {
        musicAdapter.addItem(Music(index = 76229, title = "첫눈처럼 너에게 가겠다", artist = "에일리"))
        musicAdapter.addItem(Music(index = 49082, title = "어디에도", artist = "MC THE MAX"))
        musicAdapter.addItem(Music(index = 43918, title = "사진을 보다가", artist = "바이브"))
        musicAdapter.addItem(Music(index = 76229, title = "첫눈처럼 너에게 가겠다", artist = "에일리"))
        musicAdapter.addItem(Music(index = 49082, title = "어디에도", artist = "MC THE MAX"))
        musicAdapter.addItem(Music(index = 43918, title = "사진을 보다가", artist = "바이브"))
        musicAdapter.addItem(Music(index = 76229, title = "첫눈처럼 너에게 가겠다", artist = "에일리"))
        musicAdapter.addItem(Music(index = 49082, title = "어디에도", artist = "MC THE MAX"))
        musicAdapter.addItem(Music(index = 43918, title = "사진을 보다가", artist = "바이브"))
        music_list.setHasFixedSize(true)
        music_list.adapter = musicAdapter
    }
}
