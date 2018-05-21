package com.zhonglongzhou.fastclean.data.cloud.httpkit.exception


/**
 * Created by zhonglz on 2017/12/28
 */
class ExternalNetException(val errorData: NetErrorData, message: String? = null, throwable: Throwable? = null) : BaseNetException(message, throwable)