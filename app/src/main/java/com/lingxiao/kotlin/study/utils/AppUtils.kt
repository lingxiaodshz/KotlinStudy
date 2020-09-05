package com.lingxiao.kotlin.study.utils

import android.content.Context
import android.content.pm.PackageManager

/**
 * @author luckw
 * @date   2020/9/4
 */
class AppUtils private constructor() {

    companion object {

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
    }
}