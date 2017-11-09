package net.yeoubi.turntable.viewmodel

import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.data.repository.ReserveRepository
import net.yeoubi.turntable.di.component.NetworkComponent
import net.yeoubi.turntable.view.common.AttachedView
import net.yeoubi.turntable.viewmodel.common.ViewModel
import javax.inject.Inject

/**
 * InJung Chung
 */

class MusicViewModel(
    context: Context,
    view: AttachedView
) : ViewModel(context, view) {

    @Inject
    lateinit var reserveRepository: ReserveRepository

    var musicId = ObservableField<String?>()
    var reserveList: ObservableList<Music> = ObservableArrayList<Music>()

    override fun inject(networkComponent: NetworkComponent) {
        networkComponent.inject(this)
    }

    fun next() {
        reserveRepository.pop()?.let {
            musicId.set(it.id)
            load()
        }
    }

    fun remove(item: Music) {
        reserveRepository.remove(item)
        load()
    }

    fun load() {
        reserveList.clear()
        reserveList.addAll(reserveRepository.all())
    }
}
