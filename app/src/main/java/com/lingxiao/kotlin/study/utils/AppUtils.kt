package com.lingxiao.kotlin.study.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

/**
 * @author luckw
 * @date   2020/9/4
 */
object AppUtils {

    fun getVersionName(context: Context): String {
        var versionName = ""
        try {
            val packageName = context.packageName
            versionName = context.packageManager
                    .getPackageInfo(packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return versionName
    }

    fun getMobileModel(): String {
        var model: String = Build.MODEL
        model = model?.trim { it <= ' ' } ?: ""
        return model
    }
}