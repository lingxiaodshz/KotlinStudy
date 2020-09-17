package com.lingxiao.kotlin.study.main

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.widget.Toast
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.lingxiao.kotlin.common.MultipleStatusView
import com.lingxiao.kotlin.study.R
import com.lingxiao.kotlin.study.base.BaseActivity
import com.lingxiao.kotlin.study.bean.TabEntity
import com.lingxiao.kotlin.study.main.discovery.DiscoveryFragment
import com.lingxiao.kotlin.study.main.home.HomeFragment
import com.lingxiao.kotlin.study.main.hot.HotFragment
import com.lingxiao.kotlin.study.main.mine.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author luckw
 * @date   2020/9/3
 */

class MainActivity : BaseActivity() {
    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal,
            R.mipmap.ic_discovery_normal,
            R.mipmap.ic_hot_normal,
            R.mipmap.ic_mine_normal)
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected,
            R.mipmap.ic_discovery_selected,
            R.mipmap.ic_hot_selected,
            R.mipmap.ic_mine_selected)
    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mDiscoveryFragment: DiscoveryFragment? = null
    private var mHotFragment: HotFragment? = null
    private var mMineFragment: MineFragment? = null

    private var mIndex = 0

    private var mExitTime: Long = 0

    var m: MultipleStatusView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)

    }

    private fun initTab() {
        (mTitles.indices)
                .mapTo(mTabEntities) {
                    TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it])
                }
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {
            }
        })
    }

    private fun switchFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            //首页
            0 -> {
                mHomeFragment?.let {
                    transaction.show(it)
                } ?: HomeFragment.getInstance(mTitles[index]).let {
                    mHomeFragment = it
                    transaction.add(R.id.fl_container, it, "home")
                }
            }
            1 -> {
                mDiscoveryFragment.let {
                    transaction.show(it)
                } ?: DiscoveryFragment.getInstance(mTitles[index]).let {
                    mDiscoveryFragment = it
                    transaction.add(R.id.fl_container, it, "discovery")
                }
            }
            2 -> {
                mHotFragment?.let {
                    transaction.show(mHotFragment)
                } ?: HotFragment.getInstance(mTitles[index]).let {
                    mHotFragment = it
                    transaction.add(R.id.fl_container, it, "hot")
                }
            }
            3->{
                mMineFragment?.let {
                    transaction.show(mMineFragment)
                }?:MineFragment.getInstance(mTitles[index]).let {
                    mMineFragment = it
                    transaction.add(R.id.fl_container, it, "mine")
                }
            }
            else -> {

            }
        }

        mIndex = index
        tab_layout.currentTab = index
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mDiscoveryFragment?.let { transaction.hide(it) }
        mHotFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}