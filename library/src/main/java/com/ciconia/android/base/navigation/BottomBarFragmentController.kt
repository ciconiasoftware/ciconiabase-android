package com.ciconia.android.base.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomBarFragmentController(var context: Context, var fragmentManager: FragmentManager, var bottomNavigationView: BottomNavigationView,
                                  var containerRes: Int, var rootFragments: HashMap<Int, String>) {

    /*
    rootFragments are the fragments that are placed at the beginning of a history line
    tabPointers of the respective stack
     */
    private var tabPointers = HashMap<Int, String>()
    var currentlyShown: String? = null

    fun selectNavigationItem(tab: Int) {
        val top = tabPointers[tab]
        var baseFragment: BaseBottomBarFragment? = null
        top?.let {
            if (top != currentlyShown)
                baseFragment = fragmentManager.findFragmentByTag(it) as BaseBottomBarFragment
        } ?: run {
            try {
                baseFragment = Class.forName(rootFragments[tab]).newInstance() as BaseBottomBarFragment
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
        baseFragment?.let {
            pushFragment(baseFragment!!, tab)
        }
    }

    fun pushFragment(baseFragment: BaseBottomBarFragment, tab: Int) {
        var b: Bundle? = null
        baseFragment.arguments?.let {
            b = it
        } ?: run {
            b = Bundle()
        }

        var root: String? = null
        if (rootFragments.containsValue(baseFragment.javaClass.name)) {
            if (baseFragment.javaClass.name != rootFragments[0])
                root = rootFragments[0]!!
        } else {
            root = tabPointers[tab]!!

        }

        b!!.putString("root", root)
        b!!.putInt("tab", tab)
        baseFragment.arguments = b

        val tagName = if (rootFragments.containsValue(baseFragment.javaClass.name))
            baseFragment.javaClass.name
        else
            baseFragment.javaClass.name + tab

        fragmentManager.findFragmentByTag(tagName).let {
            it?.let {
                fragmentManager.beginTransaction().show(it).commit()
            } ?: run {
                fragmentManager.beginTransaction().add(containerRes, baseFragment, tagName).commit()
            }
        }

        fragmentManager.findFragmentByTag(currentlyShown).let {
            it?.let {
                fragmentManager.beginTransaction().hide(it).commit()
            }
        }

        currentlyShown = tagName
        tabPointers[tab] = tagName
    }

    fun popFragment() {
        val old = fragmentManager.findFragmentByTag(currentlyShown) as BaseBottomBarFragment
        val newFragment: BaseBottomBarFragment

        if (rootFragments.containsValue(old.javaClass.name)) {
            newFragment = fragmentManager.findFragmentByTag(tabPointers[0]) as BaseBottomBarFragment
            currentlyShown = tabPointers[0]
            //currentTab.put(0, topFragmentAtTab(0))
            val menuItem = bottomNavigationView.menu.getItem(newFragment.tab!!)
            menuItem.isChecked = true
            fragmentManager.beginTransaction().hide(old).commit()
        } else {
            newFragment = fragmentManager.findFragmentByTag(old.rootTag) as BaseBottomBarFragment
            fragmentManager.beginTransaction().remove(old).commit()
            currentlyShown = old.rootTag
            tabPointers[old.tab!!] = old.rootTag!!
        }
        fragmentManager.beginTransaction().show(newFragment).commit()
    }

    fun currentFragment(): BaseBottomBarFragment {
        return fragmentManager.findFragmentByTag(currentlyShown) as BaseBottomBarFragment

    }

}