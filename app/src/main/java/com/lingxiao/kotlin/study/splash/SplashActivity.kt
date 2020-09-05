package com.lingxiao.kotlin.study.splash

import android.Manifest
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.lingxiao.kotlin.study.MainActivity
import com.lingxiao.kotlin.study.R
import com.lingxiao.kotlin.study.base.BaseActivity
import com.lingxiao.kotlin.study.utils.AppUtils
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

/**
 * @author luckw
 * @date   2020/9/4
 */
class SplashActivity : BaseActivity() {
    private var textTypeface: Typeface? = null
    private var descTypeFace: Typeface? = null
    private var alphaAnimation: AlphaAnimation? = null

    override fun layoutId(): Int = R.layout.activity_splash

    override fun initData() {
        textTypeface = Typeface.createFromAsset(assets,
                "fonts/Lobster-1.4.otf")
        descTypeFace = Typeface.createFromAsset(assets,
                "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun initView() {
        tv_app_name.typeface = textTypeface
        tv_splash_desc.typeface = descTypeFace
        tv_version_name.setText("v${AppUtils.getVersionName(applicationContext)}")

        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                negativeToMain()
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        checkPermission()
    }

    fun negativeToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun checkPermission() {
        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this,
                "KotlinStudy应用需要以下权限，请允许", 0, *perms)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (alphaAnimation != null) {
                        iv_web_icon.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }
}