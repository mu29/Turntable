package net.yeoubi.turntable

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var contentY: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        app_bar.addOnOffsetChangedListener { bar, offset ->
            background_image.alpha = 1 - 0.8f * (-px2dp(offset) / 184.0f)
//            contentY = if (contentY == 0.0f) content_view.y else contentY
//            content_view.y = contentY + offset * 0.7f
        }
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()
    }

    private fun px2dp(px: Int): Float {
        return px / (resources.displayMetrics.density)
    }
}
