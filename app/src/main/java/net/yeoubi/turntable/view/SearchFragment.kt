package net.yeoubi.turntable.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.yeoubi.turntable.R
import net.yeoubi.turntable.view.common.AttachedView
import net.yeoubi.turntable.view.common.ViewModelFragment
import net.yeoubi.turntable.viewmodel.common.ViewModel

class SearchFragment : ViewModelFragment() {

    override fun createViewModel(context: Context, activity: AttachedView): ViewModel? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_search, container, false)
    }
}
