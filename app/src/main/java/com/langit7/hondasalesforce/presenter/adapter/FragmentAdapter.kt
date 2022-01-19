package com.langit7.hondasalesforce.presenter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.langit7.hondasalesforce.view.fragment.BaseFragmentInterface

class FragmentAdapter(fm: FragmentManager, internal var lsFragment: List<BaseFragmentInterface>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return lsFragment[position] as Fragment
    }

    override fun getCount(): Int {
        return lsFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return lsFragment[position].title
    }
}