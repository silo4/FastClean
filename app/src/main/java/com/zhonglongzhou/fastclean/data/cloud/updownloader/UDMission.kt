package com.zhonglongzhou.fastclean.data.cloud.updownloader

/**
 * Created by zhonglz on 2018/3/7
 */

data class UDMission(var url: String, var path: String, var saveName: String, var callback: ((UDStatus) -> Unit)? = null)