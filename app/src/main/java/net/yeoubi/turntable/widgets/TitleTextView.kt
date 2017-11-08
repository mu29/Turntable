package net.yeoubi.turntable.widgets

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.widget.TextView

/**
 * InJung Chung
 */

class TitleTextView : TextView {

    constructor(context: Context) : super(context) {
        setType(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setType(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setType(context)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        setType(context)
    }

    private fun setType(context: Context) {
        typeface = Typeface.createFromAsset(context.assets, "Arciform.otf")
    }
}
