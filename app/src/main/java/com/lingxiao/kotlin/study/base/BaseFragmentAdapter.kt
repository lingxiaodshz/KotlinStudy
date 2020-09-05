package com.lingxiao.kotlin.study.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author luckw
 * @date   2020/9/4
 */
class BaseFragmentAdapter : FragmentPagerAdapter {
    private var mFragmentList: List<Fragment> = mutableListOf()
    private var mTitles: List<String>? = null

    constructor(fm: FragmentManager, fragmentList: List<Fragment>) : super(fm){
        this.mFragmentList = fragmentList
    }

    constructor(fm: FragmentManager, fragmentList: List<Fragment>, titles: List<String>) : super(fm){
        this.mTitles = titles
    }

    private fun setFragments(fm: FragmentManager, fragmentList: List<Fragment>, titles: List<String>) {
        if (this.mFragmentList != null) {
            val ft = fm.beginTransaction()
            mFragmentList.forEach{
                ft.remove(it)
            }
            ft.commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        this.mFragmentList = fragmentList
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (null!=mTitles) mTitles!![position]else ""
    }

}