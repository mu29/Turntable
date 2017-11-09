package net.yeoubi.turntable.view.adapter

import android.databinding.ObservableList

/**
 * @author InJung Chung
 */

class ItemChangeListener<T : ObservableList<*>>(
    private val onItemChanged: (T) -> Unit
) : ObservableList.OnListChangedCallback<T>() {

    override fun onItemRangeRemoved(sender: T, positionStart: Int, itemCount: Int) {
        onItemChanged(sender)
    }

    override fun onChanged(sender: T) {
        onItemChanged(sender)
    }

    override fun onItemRangeMoved(sender: T, fromPosition: Int, toPosition: Int, itemCount: Int) {
        onItemChanged(sender)
    }

    override fun onItemRangeInserted(sender: T, positionStart: Int, itemCount: Int) {
        onItemChanged(sender)
    }

    override fun onItemRangeChanged(sender: T, positionStart: Int, itemCount: Int) {
        onItemChanged(sender)
    }
}
