package net.yeoubi.turntable.viewmodel

import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import io.reactivex.rxkotlin.subscribeBy
import net.yeoubi.turntable.di.component.NetworkComponent
import net.yeoubi.turntable.view.common.AttachedView
import net.yeoubi.turntable.viewmodel.common.ViewModel
import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.data.repository.MusicRepository
import net.yeoubi.turntable.view.SearchActivity
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
    var loading: ObservableField<Boolean> = ObservableField(true)

    override fun inject(networkComponent: NetworkComponent) {
        networkComponent.inject(this)
    }

    fun search(query: String) {
        loading.set(true)
        musicRepository
            .search(query)
            .subscribeBy(
                onNext = {
                    loading.set(false)
                    musics.clear()
                    musics.addAll(it.items.filter { it.valid })
                },
                onError = Throwable::printStackTrace
            )
            .apply { disposables.add(this) }
    }

    fun openSearchActivity() {
        view.startActivity(SearchActivity::class)
    }
}
