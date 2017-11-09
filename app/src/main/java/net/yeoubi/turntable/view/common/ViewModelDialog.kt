package net.yeoubi.turntable.view.common

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import io.reactivex.disposables.CompositeDisposable
import net.yeoubi.turntable.viewmodel.common.ViewModel

/**
 * [ViewModel] 을 사용하는 모든 Dialog 의 최상위 클래스입니다.
 * Dialog 라이프사이클 이벤트를 [ViewModel] 에 전달합니다.
 *
 * @property viewModel 이 Dialog 의 뷰모델입니다. 라이프사이클을 제어하기 위해 참조합니다.
 * @author InJung Chung
 */

abstract class ViewModelDialog : DialogFragment() {

    /**
     * 다이얼로그의 결과 값을 전달받을 리스너입니다.
     */
    interface DialogListener {
        fun onComplete(vararg params: Any)
    }

    var listener: DialogListener? = null
    protected var disposables = CompositeDisposable()
    private var viewModel: ViewModel? = null

    /**
     * 라이프사이클을 제어할 뷰모델을 생성합니다.
     *
     * @param context 어플리케이션의 context 입니다. [android.content.Context.getString] 등을 위해 사용됩니다.
     * @param dialog close 메소드를 실행하기 위한 인터페이스입니다.
     * @return 이 클래스가 라이프사이클을 제어할 뷰모델을 반환합니다.
     */
    protected abstract fun createViewModel(context: Context, dialog: AttachedView): ViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = activity as AppCompatActivity
        viewModel = createViewModel(activity.applicationContext, AttachedDialog(this))
    }

    override fun onStart() {
        super.onStart()
        viewModel?.onStart()
        dialog.window.let {
            val width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                300f,
                context.resources.displayMetrics
            )
            val params = WindowManager.LayoutParams()

            params.copyFrom(it.attributes)
            params.dimAmount = 0.5f
            params.width = width.toInt()
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            it.attributes = params
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel?.onStop()
        disposables.clear()
    }

    /**
     * @return 뒤로가기 버튼, 배경 터치로 종료가 불가능한 다이얼로그를 생성합니다.
     */
    protected fun buildDialog(view: View): AlertDialog {
        val builder = AlertDialog.Builder(activity)
        builder.setView(view).setCancelable(false)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(0))
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }
}
