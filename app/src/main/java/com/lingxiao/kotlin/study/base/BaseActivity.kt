package com.lingxiao.kotlin.study.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.lingxiao.kotlin.common.MultipleStatusView
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * @author luckw
 * @date   2020/9/3
 */

abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        start()
        initListener()
    }

    protected open fun initData() {
    }

    protected open fun initView() {
    }

    protected open fun start() {
    }

    private fun initListener() {
        mLayoutStatusView?.setOnRetryClickListener(mRetryClickListener)
    }

    //为类增加open，class就可以被继承了
    //为方法增加open，那么方法就可以被重写了
    open val mRetryClickListener:View.OnClickListener = View.OnClickListener {
        start()
    }

    abstract fun layoutId(): Int

    fun closeKeyboard(editText: EditText, context: Context) {

        var imm = context.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
    fun openKeyboard(editText: EditText, context: Context) {
        var imm = context.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        val sf = StringBuffer()
        for ((i, str) in perms.withIndex()) {
            sf.append(str)
            if (i < perms.size - 1) {
                sf.append("\n")
            }
        }
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this,
                    "已经拒绝权限" + sf + "并不再询问", Toast.LENGTH_LONG).show()
            AppSettingsDialog.Builder(this)
                    .setRationale("此功能需要" + sf + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("确定")
                    .setNegativeButton("下次再说")
                    .build()
                    .show()

        }

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

}