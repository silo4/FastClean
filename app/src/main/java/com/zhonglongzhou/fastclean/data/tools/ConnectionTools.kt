package com.zhonglongzhou.fastclean.data.tools

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by zhonglz on 2018/5/4
 */

object ConnectionTools{
    /**
     * 是否连接网络
     */
    fun isThereInternetConnection(context: Context): Boolean {
        val mgr: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return mgr.activeNetworkInfo?.let {
            mgr.activeNetworkInfo.isConnectedOrConnecting
        } ?: false
    }
}