package com.zhonglongzhou.fastclean.data.cloud.businessB.config

/**
 * Created by zhonglz on 2018/5/8
 */

object BusinessBConstant{
    private const val SCHEME_HTTP = "http"
    private const val SCHEME_HTTPS = "https"
    private const val HOST_TESTING = "sandbox.zhonglongzhou.com"
    private const val HOST_PRODUCTION = "production.zhonglongzhou.com"


    val BASE_URL = when (BusinessBEnviroment.env){
        BusinessBEnviroment.EnmBusinessBEnvironment.TESTING -> buildBaseUrl(SCHEME_HTTP, HOST_TESTING)
        BusinessBEnviroment.EnmBusinessBEnvironment.PRODUCTION -> buildBaseUrl(SCHEME_HTTPS, HOST_PRODUCTION)
        BusinessBEnviroment.EnmBusinessBEnvironment.OTHER -> ""
    }

    private fun buildBaseUrl(scheme: String, host: String) : String{
        return StringBuilder(scheme)
                .append("://")
                .append(host)
                .toString()
    }

    object Path{
        val BUSINESS_B = "/businessB"
    }
}