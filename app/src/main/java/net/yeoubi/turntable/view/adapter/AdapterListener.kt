package net.yeoubi.turntable.view.adapter


/**
 * RecyclerView 의 아이템에서 발생하는 이벤트를 뷰에서 처리하기 위한 인터페이스입니다.
 *
 * @author InJung Chung
 */

interface AdapterListener {

    fun onAction(actionId: Int, vararg params: Any)

    companion object {
        val CLICK = 0
    }
}
