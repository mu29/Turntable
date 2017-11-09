package net.yeoubi.turntable.viewmodel

import android.content.Context
import android.databinding.ObservableField
import net.yeoubi.turntable.common.constants.Extras
import net.yeoubi.turntable.di.component.NetworkComponent
import net.yeoubi.turntable.view.common.AttachedView
import net.yeoubi.turntable.viewmodel.common.ViewModel

/**
 * InJung Chung
 */

class MusicViewModel(
    context: Context,
    view: AttachedView
) : ViewModel(context, view) {

    var musicId = ObservableField<String>()

    override fun inject(networkComponent: NetworkComponent) {
        networkComponent.inject(this)
    }

    fun init() {
        view.getExtra(Extras.ID)?.let {
            musicId.set(it as? String)
        }
    }
}
