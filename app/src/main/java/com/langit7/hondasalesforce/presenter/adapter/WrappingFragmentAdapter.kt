package com.langit7.hondasalesforce.presenter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.langit7.hondasalesforce.view.fragment.BaseFragmentInterface
import xyz.santeri.wvp.WrappingFragmentPagerAdapter

class WrappingFragmentAdapter(fm: FragmentManager, internal var listitem: List<Fragment>) : WrappingFragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return listitem[position]
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return listitem.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return (listitem[position] as BaseFragmentInterface).title
    }


}