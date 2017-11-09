package net.yeoubi.turntable.view.common

import android.app.Activity
import android.os.Bundle
import java.net.URISyntaxException
import kotlin.reflect.KClass

/**
 * [net.yeoubi.turntable.viewmodel.common.ViewModel] 에서 뷰 관련 로직을 실행하기 위한 인터페이스입니다.
 *
 * @author InJung Chung
 */

interface AttachedView {

    /**
     * 해당 Activity 를 실행합니다.
     *
     * @param activityClass 실행할 Activity 의 클래스입니다.
     */
    fun startActivity(activityClass: KClass<*>)
    fun startActivity(activityClass: Class<Activity>)

    /**
     * 해당 Activity 를 Extra 를 넣어 실행합니다.
     *
     * @param activityClass 실행할 Activity 의 클래스입니다.
     * @param extras Extra 로 들어갈 key, value 입니다.
     */
    fun startActivity(activityClass: KClass<*>, vararg extras: Any)
    fun startActivity(activityClass: Class<Activity>, vararg extras: Any)

    /**
     * 이 뷰를 닫습니다.
     */
    fun close()

    /**
     * 해당 URL 로 이동합니다.
     *
     * @param url 실행할 Activity 의 클래스입니다.
     * @throws URISyntaxException
     */
    @Throws(URISyntaxException::class)
    fun openUrl(url: String)

    /**
     * 해당 다이얼로그를 보여줍니다.
     *
     * @param dialogClass 보여줄 Dialog 의 클래스입니다.
     * @param title 다이얼로그의 제목입니다.
     */
    fun showDialog(dialogClass: KClass<*>, title: String)
    fun showDialog(dialogClass: Class<*>, title: String)

    /**
     * 현재 Activity 의 Extra 를 받아옵니다.
     *
     * @param key 받아올 extra 의 키입니다.
     */
    fun getExtra(key: String): Any?
}
