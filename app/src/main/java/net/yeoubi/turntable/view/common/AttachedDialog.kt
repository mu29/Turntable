package net.yeoubi.turntable.view.common

import android.app.Activity
import android.content.Intent
import android.support.v4.app.DialogFragment
import java.lang.ref.WeakReference
import java.net.URISyntaxException
import kotlin.reflect.KClass

/**
 * [AttachedView] 를 구현합니다.
 *
 * @property weakDialog Dialog 에 대한 참조입니다.
 * @author InJung Chung
 */

class AttachedDialog(dialog: DialogFragment) : AttachedView {

    private val weakDialog: WeakReference<DialogFragment> = WeakReference(dialog)

    /**
     * 해당 Activity 를 실행합니다. Kotlin Activity 를 실행하는데 사용됩니다.
     *
     * @param activityClass 실행할 Activity 의 클래스입니다.
     */
    override fun startActivity(activityClass: KClass<*>) {
        val activity = weakDialog.get()?.activity
        if (activity != null && !activity.isFinishing) {
            if (activity::class == activityClass) {
                return
            }

            activity.startActivity(Intent(activity, activityClass.java))
        }
    }

    /**
     * 해당 Activity 를 Extra 를 넣어 실행합니다. Kotlin Activity 를 실행하는데 사용됩니다.
     *
     * @param activityClass 실행할 Activity 의 클래스입니다.
     * @param extras Extra 로 들어갈 key, value 입니다.
     */
    override fun startActivity(activityClass: KClass<*>, vararg extras: Any) {
        val activity = weakDialog.get()?.activity
        if (activity != null && !activity.isFinishing) {
            if (activity::class == activityClass) {
                return
            }

            val intent = Intent(activity, activityClass.java)
            val keys = extras.filterIndexed { index, _ -> index % 2 == 0 } .map { it as String }
            val values = extras.filterIndexed { index, _ -> index % 2 == 1 }
            keys.forEachIndexed { index, key ->
                val value = values[index]
                when(value) {
                    is Int -> intent.putExtra(key, value)
                    is Short -> intent.putExtra(key, value)
                    is Long -> intent.putExtra(key, value)
                    is String -> intent.putExtra(key, value)
                    is Boolean -> intent.putExtra(key, value)
                }
            }
            activity.startActivity(intent)
        }
    }

    /**
     * 해당 Activity 를 실행합니다. Java Activity 를 실행하는데 사용됩니다.
     *
     * @param activityClass 실행할 Activity 의 클래스입니다.
     */
    override fun startActivity(activityClass: Class<Activity>) {
        val activity = weakDialog.get()?.activity
        if (activity != null && !activity.isFinishing) {
            if (activity::class == activityClass) {
                return
            }

            activity.startActivity(Intent(activity, activityClass))
        }
    }

    /**
     * 해당 Activity 를 실행합니다. Java Activity 를 실행하는데 사용됩니다.
     *
     * @param activityClass 실행할 Activity 의 클래스입니다.
     * @param extras Extra 로 들어갈 key, value 입니다.
     */
    override fun startActivity(activityClass: Class<Activity>, vararg extras: Any) {
        val activity = weakDialog.get()?.activity
        if (activity != null && !activity.isFinishing) {
            if (activity::class == activityClass) {
                return
            }

            val intent = Intent(activity, activityClass)
            val keys = extras.filterIndexed { index, _ -> index % 2 == 0 } .map { it as String }
            val values = extras.filterIndexed { index, _ -> index % 2 == 1 }
            keys.forEachIndexed { index, key ->
                val value = values[index]
                when(value) {
                    is Int -> intent.putExtra(key, value)
                    is Short -> intent.putExtra(key, value)
                    is Long -> intent.putExtra(key, value)
                    is String -> intent.putExtra(key, value)
                    is Boolean -> intent.putExtra(key, value)
                }
            }
            activity.startActivity(intent)
        }
    }

    /**
     * 이 Dialog 를 닫습니다.
     */
    override fun close() {
        weakDialog.get()?.dismiss()
    }

    /**
     * 해당 URL 로 이동합니다. [android.app.Activity.startActivity] 를 사용합니다.
     *
     * @param url 실행할 Activity 의 클래스입니다.
     * @throws URISyntaxException
     */
    @Throws(URISyntaxException::class)
    override fun openUrl(url: String) {
        val activity = weakDialog.get()?.activity
        if (activity != null && !activity.isFinishing) {
            activity.startActivity(Intent.parseUri(url, 0))
        }
    }

    /**
     * 해당 다이얼로그를 보여줍니다. Kotlin 다이얼로그를 보여주는데 사용됩니다.
     *
     * @param dialogClass 보여줄 Dialog 의 클래스입니다.
     * @param title 다이얼로그의 제목입니다.
     */
    override fun showDialog(dialogClass: KClass<*>, title: String) {
        if (ViewModelDialog::class.java.isAssignableFrom(dialogClass.java)) {
            showDialog(dialogClass.java, title)
        }
    }

    /**
     * 해당 다이얼로그를 보여줍니다. Java 다이얼로그를 보여주는데 사용됩니다.
     *
     * @param dialogClass 보여줄 Dialog 의 클래스입니다.
     * @param title 다이얼로그의 제목입니다.
     */
    override fun showDialog(dialogClass: Class<*>, title: String) {
        val activity = weakDialog.get()?.activity
        if (activity == null || activity.isFinishing) {
            return
        }

        close()
        (dialogClass.newInstance() as ViewModelDialog).show(activity.supportFragmentManager, title)
    }

    /**
     * 현재 Activity 의 Extra 를 받아옵니다.
     *
     * @param key 받아올 extra 의 키입니다.
     */
    override fun getExtra(key: String): Any? {
        val activity = weakDialog.get()?.activity
        if (activity == null || activity.isFinishing) {
            return null
        }

        return activity.intent.extras.get(key)
    }
}
