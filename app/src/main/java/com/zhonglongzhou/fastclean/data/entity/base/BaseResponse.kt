package com.zhonglongzhou.fastclean.data.entity.base

import com.google.gson.annotations.SerializedName

/**
 * Created by zhonglz on 2018/5/8
 */

open class BaseResponse<T>{

    constructor()

    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("statusCode")
    var statusCode: Int? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("data")
    var data: T? = null
}