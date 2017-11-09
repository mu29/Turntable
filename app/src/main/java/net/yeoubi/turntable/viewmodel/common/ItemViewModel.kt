package net.yeoubi.turntable.viewmodel.common

import android.databinding.BaseObservable
import net.yeoubi.turntable.view.common.AttachedView
import java.util.HashMap

/**
 * @author InJung Chung
 */

open class ItemViewModel<T> : BaseObservable {

    var item: T
        protected set
    var activity: AttachedView? = null
    var position: Int = 0
    var extras: HashMap<String, Any> = hashMapOf()

    internal constructor(item: T) {
        this.item = item
    }

    internal constructor(item: T, extras: HashMap<String, Any>) {
        this.item = item
        this.extras = extras
    }
}
