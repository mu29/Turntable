package net.yeoubi.turntable.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.RxTextView

import net.yeoubi.turntable.R
import net.yeoubi.turntable.common.constants.Extras
import net.yeoubi.turntable.databinding.FragmentSearchBinding
import net.yeoubi.turntable.view.adapter.ItemChangeListener
import net.yeoubi.turntable.view.adapter.RecyclerAdapter
import net.yeoubi.turntable.view.common.AttachedView
import net.yeoubi.turntable.view.common.ViewModelFragment
import net.yeoubi.turntable.viewmodel.SearchViewModel
import net.yeoubi.turntable.viewmodel.common.ViewModel
import net.yeoubi.turntable.viewmodel.item.MusicItemViewModel
import java.util.concurrent.TimeUnit

class SearchFragment : ViewModelFragment() {

    interface OnReserveListener {
        fun onReserve()
    }
    var onReserveListener: OnReserveListener? = null

    lateinit var viewModel: SearchViewModel
    lateinit var binding: FragmentSearchBinding
    private lateinit var musicAdapter: RecyclerAdapter

    override fun createViewModel(context: Context, activity: AttachedView): ViewModel? {
        viewModel = SearchViewModel(context, activity)
        return viewModel
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.view = this
        binding.viewModel = viewModel

        setRecyclerView()
        setEditText()

        return binding.root
    }

    fun showBackButton(): Boolean {
        return activity is SearchActivity
    }

    private fun setRecyclerView() {
        musicAdapter = RecyclerAdapter(activity)
        binding.listMusic.apply {
            adapter = musicAdapter
            setHasFixedSize(true)
        }
        viewModel.musics.addOnListChangedCallback(ItemChangeListener {
            musicAdapter.reset(
                it.map(::MusicItemViewModel), R.layout.item_music,
                Extras.ON_CLICK, { onReserveListener?.onReserve() }
            )
        })
    }

    private fun setEditText() {
        RxTextView
            .textChanges(binding.etInput)
            .filter { !it.isEmpty() }
            .doOnNext { viewModel.loading.set(true) }
            .debounce(1000, TimeUnit.MILLISECONDS)
            .subscribe { viewModel.search(it.toString()) }
            .apply { disposables.add(this) }
    }
}
