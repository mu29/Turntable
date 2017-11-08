package net.yeoubi.turntable

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.yeoubi.turntable.utils.DimensionUtils

class MainActivity : AppCompatActivity() {

    private var contentY: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        app_bar.addOnOffsetChangedListener { bar, offset ->
            background_image.alpha = 1 - 0.8f * (-DimensionUtils.context(context = this).toDp(offset) / 184.0f)
//            contentY = if (contentY == 0.0f) content_view.y else contentY
//            content_view.y = contentY + offset * 0.7f
        }
    }
}
