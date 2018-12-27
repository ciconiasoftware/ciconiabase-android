package com.ciconia.android.base.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ciconia.android.base.search.MaterialSearchView
import com.google.android.material.floatingactionbutton.FloatingActionButton


abstract class BaseBottomBarActivity : AppCompatActivity() {

    abstract fun setupFragment(title: String?, tabSelected: Int)

    abstract fun getNavigationController(): BottomBarFragmentController

    abstract fun getSearchView(): MaterialSearchView?

    abstract fun getSwipeToRefreshLayout(): SwipeRefreshLayout?

    abstract fun getFab(): FloatingActionButton?

    override fun onBackPressed() {
        if (getSearchView() != null && getSearchView()!!.isSearchOpen) {
            getSearchView()!!.closeSearch()
        } else {
            val baseFragment = supportFragmentManager.findFragmentByTag(getNavigationController().currentlyShown) as BaseBottomBarFragment
            baseFragment.rootTag?.let {
                getNavigationController().popFragment()
            } ?: run {
                finish()
            }
        }
    }
}