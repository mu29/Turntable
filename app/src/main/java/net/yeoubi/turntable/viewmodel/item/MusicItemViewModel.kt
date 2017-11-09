package net.yeoubi.turntable.viewmodel.item

import net.yeoubi.turntable.common.constants.Extras
import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.data.repository.ReserveRepository
import net.yeoubi.turntable.di.component.NetworkComponent
import net.yeoubi.turntable.view.MusicActivity
import net.yeoubi.turntable.viewmodel.common.ItemViewModel
import javax.inject.Inject

/**
 * @author InJung Chung
 */

class MusicItemViewModel(item: Music) : ItemViewModel<Music>(item) {

    @Inject
    lateinit var reserveRepository: ReserveRepository

    override fun inject(networkComponent: NetworkComponent) {
        networkComponent.inject(this)
    }

    @Suppress("UNCHECKED_CAST")
    fun onClick() {
        reserveRepository.push(item)
        activity?.startActivity(MusicActivity::class)
        val onClickHandler = extras[Extras.ON_CLICK] as? () -> Unit
        onClickHandler?.invoke()
    }
}
