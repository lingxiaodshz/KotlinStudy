package com.lingxiao.kotlin.study.main.home

import android.os.Bundle
import com.lingxiao.kotlin.study.R
import com.lingxiao.kotlin.study.base.BaseFragment

/**
 * @author luckw
 * @date   2020/9/6
 */
class HomeFragment : BaseFragment() {
    companion object{
        fun getInstance(title: String): HomeFragment {
            var fragment = HomeFragment()
            var bundle = Bundle()
            bundle.putString("title", title)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

}