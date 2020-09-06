package com.lingxiao.kotlin.study.main.mine

import android.os.Bundle
import com.lingxiao.kotlin.study.R
import com.lingxiao.kotlin.study.base.BaseFragment
import com.lingxiao.kotlin.study.main.hot.HotFragment

/**
 * @author luckw
 * @date   2020/9/6
 */
class MineFragment : BaseFragment() {
    companion object{
        fun getInstance(title: String): MineFragment {
            var fragment = MineFragment()
            var bundle = Bundle()
            bundle.putString("title", title)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

}