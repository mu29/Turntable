package net.yeoubi.turntable.view.common

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import net.yeoubi.turntable.viewmodel.common.ViewModel

/**
 * [ViewModel] 을 사용하는 모든 Fragment 의 최상위 클래스입니다.
 * Fragment 라이프사이클 이벤트를 [ViewModel] 에 전달합니다.
 *
 * @property viewModel 이 Fragment 의 뷰모델입니다. 라이프사이클을 제어하기 위해 참조합니다.
 * @author InJung Chung
 */

abstract class ViewModelFragment : Fragment() {

    protected var disposables = CompositeDisposable()
    private var viewModel: ViewModel? = null

    /**
     * 라이프사이클을 제어할 뷰모델을 생성합니다.
     *
     * @param context 어플리케이션의 context 입니다. [android.content.Context.getString] 등을 위해 사용됩니다.
     * @param activity [android.app.Activity.startActivity] 메소드를 실행하기 위한 인터페이스입니다.
     * @return 이 클래스가 라이프사이클을 제어할 뷰모델을 반환합니다.
     */
    protected abstract fun createViewModel(context: Context, activity: AttachedView): ViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = activity as AppCompatActivity
        viewModel = createViewModel(activity.applicationContext, AttachedActivity(activity))
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
