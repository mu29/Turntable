package net.yeoubi.turntable.widgets

import android.graphics.Typeface
import android.os.Build
import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import net.yeoubi.turntable.R

/**
 * InJung Chung
 */

class NotoTextView : TextView {

    constructor(context: Context) : super(context) {
        setType(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setType(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setType(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        setType(context, attrs)
    }

    private fun setType(context: Context) {
        this.typeface = Typeface.createFromAsset(context.assets, "NotoSansRegular.otf")
    }

    private fun setType(context: Context, attrs: AttributeSet) {
        val weight: String? = context.obtainStyledAttributes(attrs, R.styleable.NotoTextView).getString(R.styleable.NotoTextView_weight)
        this.typeface = when (weight) {
            "light" -> Typeface.createFromAsset(context.assets, "NotoSansLight.otf")
            "bold" -> Typeface.createFromAsset(context.assets, "NotoSansBold.otf")
            else -> Typeface.createFromAsset(context.assets, "NotoSansRegular.otf")
        }
    }
}
