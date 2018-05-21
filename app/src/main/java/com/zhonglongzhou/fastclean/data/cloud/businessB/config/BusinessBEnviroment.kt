package com.zhonglongzhou.fastclean.data.cloud.businessB.config

/**
 * Created by zhonglz on 2018/5/8
 */

object BusinessBEnviroment{
    var env = EnmBusinessBEnvironment.TESTING

    fun init(env: EnmBusinessBEnvironment){
        this.env = env
    }

    enum class EnmBusinessBEnvironment{
        TESTING, //测试环境
        PRODUCTION, //正式环境
        OTHER, //其他环境
    }
}