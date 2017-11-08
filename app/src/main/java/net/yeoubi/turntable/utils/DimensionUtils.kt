package net.yeoubi.turntable.utils

import android.content.Context
import android.util.TypedValue

/**
 * InJung Chung
 */

class DimensionUtils(private val context: Context) {

    fun tpPx(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }

    fun toDp(px: Int): Float {
        return px / (context.resources.displayMetrics.density)
    }

    companion object {
        fun context(context: Context): DimensionUtils {
            return DimensionUtils(context)
        }
    }
}
