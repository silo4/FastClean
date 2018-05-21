package com.zhonglongzhou.fastclean.presentation.mvibase

/**
 * Created by zhonglz on 2018/5/4
 */

enum class EnmLceStatus {
    /**
     * Request has succeeded and response contains data
     */
    SUCCESS,
    /**
     * Request failed
     */
    FAILURE,
    /**
     * Request is send. Waiting for a response
     */
    IN_FLIGHT,
}