package com.lingxiao.kotlin.study.main.discovery

import android.os.Bundle
import com.lingxiao.kotlin.study.R
import com.lingxiao.kotlin.study.base.BaseFragment
import com.lingxiao.kotlin.study.main.home.HomeFragment

/**
 * @author luckw
 * @date   2020/9/6
 */
class DiscoveryFragment : BaseFragment() {
    companion object{
        fun getInstance(title: String): DiscoveryFragment {
            var fragment = DiscoveryFragment()
            var bundle = Bundle()
            bundle.putString("title", title)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_hot
}