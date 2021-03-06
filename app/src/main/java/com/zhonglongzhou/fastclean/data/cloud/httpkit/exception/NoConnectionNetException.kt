package com.zhonglongzhou.fastclean.data.cloud.httpkit.exception


/**
 * Created by zhonglz on 2017/12/28
 */
class NoConnectionNetException : BaseNetException {
    constructor(message: String?) : super(message)
    constructor(throwable: Throwable?) : super(throwable)
    constructor(message: String?, throwable: Throwable?) : super(message, throwable)
}