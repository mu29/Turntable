package net.yeoubi.turntable.viewmodel.common

import android.content.Context
import android.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable
import net.yeoubi.turntable.di.component.DaggerNetworkComponent
import net.yeoubi.turntable.di.component.NetworkComponent
import net.yeoubi.turntable.di.module.NetworkModule
import net.yeoubi.turntable.view.common.AttachedView

/**
 * 이 프로젝트에서 사용되는 뷰모델의 상위 클래스입니다.
 * 안드로이드 데이터 바인딩 라이브러리에서 사용할 수 있도록 BaseObservable 을 상속받습니다.
 * [onStop] 에서 [disposables] 를 정리해줍니다.
 *
 * @author InJung Chung
 */

abstract class ViewModel(
    protected val context: Context,
    protected val view: AttachedView
) : BaseObservable() {

    protected val disposables = CompositeDisposable()

    init {
        inject(networkComponent)
    }

    abstract fun inject(networkComponent: NetworkComponent)

    fun onStart() {}

    fun onStop() {
        disposables.clear()
    }

    companion object {
        val networkComponent: NetworkComponent = DaggerNetworkComponent.builder()
            .networkModule(NetworkModule())
            .build()
    }
}
