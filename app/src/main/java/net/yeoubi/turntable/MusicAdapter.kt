package net.yeoubi.turntable

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_music.view.*

/**
 * InJung Chung
 */

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    private val itemList: MutableList<Music> = ArrayList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemView?.let {
            it.index.text = itemList[position].index.toString()
            it.title.text = itemList[position].title
            it.artist.text = itemList[position].artist
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.item_music, parent, false)
        return ViewHolder(view)
    }

    fun addItem(item: Music) {
        itemList.add(item)
    }

    fun addItems(items: List<Music>) {
        itemList.addAll(items)
    }

    fun clear() {
        itemList.clear()
    }
}
