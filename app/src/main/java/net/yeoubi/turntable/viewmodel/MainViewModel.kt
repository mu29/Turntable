package net.yeoubi.turntable.viewmodel

import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import io.reactivex.rxkotlin.subscribeBy
import net.yeoubi.turntable.di.component.NetworkComponent
import net.yeoubi.turntable.view.common.AttachedView
import net.yeoubi.turntable.viewmodel.common.ViewModel
import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.data.repository.MusicRepository
import javax.inject.Inject

/**
 * @author InJung Chung
 */

class MainViewModel(
    context: Context,
    view: AttachedView
) : ViewModel(context, view) {

    @Inject
    lateinit var musicRepository: MusicRepository

    var musics: ObservableList<Music> = ObservableArrayList()

    override fun inject(networkComponent: NetworkComponent) {
        networkComponent.inject(this)
    }

    fun search(query: String) {
        musicRepository
            .search(query)
            .subscribeBy(
                onNext = {
                    musics.clear()
                    musics.addAll(it.items.filter { it.valid })
                },
                onError = Throwable::printStackTrace
            )
            .apply { disposables.add(this) }
    }
}
