package com.lingxiao.kotlin.study

import android.app.Activity
import android.os.Bundle
import com.lingxiao.kotlin.common.MultipleStatusView

/**
 * @author luckw
 * @date   2020/9/3
 */

class MainActivity : Activity() {
    var m: MultipleStatusView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}