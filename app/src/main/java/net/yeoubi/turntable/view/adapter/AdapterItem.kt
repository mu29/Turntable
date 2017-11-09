package net.yeoubi.turntable.view.adapter

import java.util.HashMap


/**
 * 뷰모델과 레이아웃을 받아 저장하는 데이터 홀더입니다.
 *
 * @author InJung Chung
 */

class AdapterItem<T> {

    internal var viewModel: T
        private set
    internal var layoutRes: Int = 0
        private set
    internal val extras = HashMap<String, Any>()

    constructor(viewModel: T, layoutRes: Int) {
        this.viewModel = viewModel
        this.layoutRes = layoutRes
    }

    constructor(viewModel: T, layoutRes: Int, vararg extras: Any) {
        this.viewModel = viewModel
        this.layoutRes = layoutRes

        if (extras.size % 2 != 0) {
            throw IllegalArgumentException("데이터는 키/값이 쌍을 이루어야 합니다.")
        }

        for (i in 0 until extras.size / 2)
            this.extras.put(extras[i * 2] as String, extras[i * 2 + 1])
    }

}
