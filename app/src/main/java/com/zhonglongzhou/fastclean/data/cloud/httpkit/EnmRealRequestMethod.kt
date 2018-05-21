package com.zhonglongzhou.fastclean.data.cloud.httpkit

/**
 * Created by zhonglz on 2018/5/8
 */
enum class EnmRealRequestMethod(val value: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH"),
    HEAD("HEAD")
}