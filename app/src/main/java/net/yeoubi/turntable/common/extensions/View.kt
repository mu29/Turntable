package net.yeoubi.turntable.common.extensions

import android.view.View
import android.view.ViewGroup

/**
 * InJung Chung
 */

fun View.updateMargins(left: Int, top: Int, right: Int, bottom: Int) {
    val density = context.resources.displayMetrics.density
    val params = this.layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(
        (left * density).toInt(),
        (top * density).toInt(),
        (right * density).toInt(),
        (bottom * density).toInt()
    )
    this.requestLayout()
}
