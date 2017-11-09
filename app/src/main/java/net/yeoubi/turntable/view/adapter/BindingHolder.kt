package net.yeoubi.turntable.view.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * @author InJung Chung
 */

class BindingHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root) {

    init {
        val context = binding.root.context
    }
}
