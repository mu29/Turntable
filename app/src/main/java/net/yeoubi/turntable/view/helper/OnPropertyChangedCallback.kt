package net.yeoubi.turntable.view.helper

import android.databinding.Observable

/**
 * InJung Chung
 */

class OnPropertyChangedCallback (
    private val onChange: () -> Unit
): Observable.OnPropertyChangedCallback() {

    override fun onPropertyChanged(value: Observable?, p1: Int) {
        onChange()
    }
}
