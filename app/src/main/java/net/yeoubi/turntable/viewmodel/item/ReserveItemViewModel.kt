package net.yeoubi.turntable.viewmodel.item

import net.yeoubi.turntable.common.constants.Extras
import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.di.component.NetworkComponent
import net.yeoubi.turntable.viewmodel.common.ItemViewModel

/**
 * InJung Chung
 */

class ReserveItemViewModel(item: Music) : ItemViewModel<Music>(item) {

    override fun inject(networkComponent: NetworkComponent) {}

    @Suppress("UNCHECKED_CAST")
    fun onClick() {
        val onClickHandler = extras[Extras.ON_CLICK] as? (Music) -> Unit
        onClickHandler?.invoke(item)
    }
}
