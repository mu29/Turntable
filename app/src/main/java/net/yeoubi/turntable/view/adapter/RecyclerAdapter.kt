package net.yeoubi.turntable.view.adapter

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import net.yeoubi.turntable.BR
import net.yeoubi.turntable.view.common.AttachedActivity
import net.yeoubi.turntable.viewmodel.common.ItemViewModel
import java.util.ArrayList
import java.util.HashMap

/**
 * @author InJung Chung
 */

class RecyclerAdapter(
    val activity: Activity
) : RecyclerView.Adapter<BindingHolder<*>>() {

    private val items: ArrayList<AdapterItem<ItemViewModel<*>>> = ArrayList()

    val isEmpty: Boolean
        get() = items.isEmpty()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BindingHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder<*>, position: Int) {
        val item = getItem(position)

        item.activity = AttachedActivity(activity as AppCompatActivity)
        item.position = position
        item.extras = getExtras(position)
        holder.binding.setVariable(BR.viewModel, item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].layoutRes
    }

    fun getItem(position: Int): ItemViewModel<*> {
        return items[position].viewModel
    }

    fun getExtras(position: Int): HashMap<String, Any> {
        return items[position].extras
    }

    fun add(item: AdapterItem<ItemViewModel<*>>) {
        items.add(item)
    }

    fun <I : ItemViewModel<*>> addAll(items: Collection<I>, viewType: Int) {
        for (item in items) {
            this.items.add(AdapterItem(item, viewType))
        }
    }

    fun <I : ItemViewModel<*>> addAll(items: Collection<I>, viewType: Int, vararg extras: Any) {
        for (item in items) {
            this.items.add(AdapterItem(item, viewType, *extras))
        }
    }

    fun clear() {
        items.clear()
    }

    fun <T : ItemViewModel<*>> reset(items: Collection<T>, layoutRes: Int) {
        clear()
        addAll(items, layoutRes)
        notifyDataSetChanged()
    }

    fun <T : ItemViewModel<*>> reset(items: Collection<T>, layoutRes: Int, vararg extras: Any) {
        clear()
        addAll(items, layoutRes, *extras)
        notifyDataSetChanged()
    }
}
