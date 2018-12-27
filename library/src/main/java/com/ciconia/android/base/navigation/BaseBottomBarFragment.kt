package com.ciconia.android.base.navigation

import android.os.Bundle
import android.text.BoringLayout
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseBottomBarFragment : Fragment() {

    abstract fun getFragmentTitle(): String?
    abstract fun getMenu(): Int?
    abstract fun isRefreshing(): Boolean
    abstract fun onRefresh()
    abstract fun onFabPressed()
    abstract fun hasSearchView(): Boolean
    abstract fun hasFab(): Boolean

    var rootTag: String? = null
    var tab: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.arguments?.let {
            rootTag = it.getString("root")
            tab = it.getInt("tab")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseBottomBarActivity).setupFragment(getFragmentTitle(), tab!!)
        (activity as BaseBottomBarActivity).getFab()?.let {
            if (hasFab()) {
                it.show()
                it.setOnClickListener {
                    onFabPressed()
                }
            } else it.hide()
        }
        (activity as BaseBottomBarActivity).getSwipeToRefreshLayout()?.let {
            it.isEnabled = isRefreshing()
        }
        setHasOptionsMenu(getMenu() != 0)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            (activity as BaseBottomBarActivity).setupFragment(getFragmentTitle(), tab!!)
            (activity as BaseBottomBarActivity).getFab()?.let {
                if (hasFab()) {
                    it.show()
                    it.setOnClickListener {
                        onFabPressed()
                    }
                } else it.hide()
            }
            (activity as BaseBottomBarActivity).getSwipeToRefreshLayout()?.let {
                it.isEnabled = isRefreshing()
            }
        } else {
            if (hasSearchView() && (activity as BaseBottomBarActivity).getSearchView()!!.isSearchOpen)
                (activity as BaseBottomBarActivity).getSearchView()!!.closeSearch()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (hasSearchView())
            (activity as BaseBottomBarActivity).getSearchView()!!.closeSearch()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        getMenu()?.let {
            inflater?.inflate(it, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }


}