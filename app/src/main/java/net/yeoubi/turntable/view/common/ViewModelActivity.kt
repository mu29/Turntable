package net.yeoubi.turntable.view.common

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import net.yeoubi.turntable.viewmodel.common.ViewModel

/**
 * @author InJung Chung
 */

abstract class ViewModelActivity : AppCompatActivity() {

    protected val disposables = CompositeDisposable()
    private var viewModel: ViewModel? = null

    /**
     * 라이프사이클을 제어할 뷰모델을 생성합니다.
     *
     * @param context 어플리케이션의 context 입니다. [android.content.Context.getString] 등을 위해 사용됩니다.
     * @param activity [android.app.Activity.startActivity] 메소드를 실행하기 위한 인터페이스입니다.
     * @return 이 클래스가 라이프사이클을 제어할 뷰모델을 반환합니다.
     */
    abstract fun createViewModel(context: Context, activity: AttachedView): ViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel(application, AttachedActivity(this))
    }

    override fun onStart() {
        super.onStart()
        viewModel?.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel?.onStop()
        disposables.clear()
    }
}
