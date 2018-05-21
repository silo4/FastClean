package com.zhonglongzhou.fastclean.data.cloud.httpkit

import com.zhonglongzhou.fastclean.data.tools.JsonTools
import com.zhonglongzhou.fastclean.domain.common.base.BasePackageOut

/**
 * Created by zhonglz on 2018/5/8
 */
open class RealRequest{
    var mBaseUrl: String
    var mPath: String
    lateinit var mBody: ByteArray
    var mMethod: EnmRealRequestMethod
    var mHeaders: Map<String, String>? = null

    constructor(mBaseUrl: String, mPath: String, mBody: ByteArray, mMethod: EnmRealRequestMethod) {
        this.mBaseUrl = mBaseUrl
        this.mPath = mPath
        this.mBody = mBody
        this.mMethod = mMethod
    }

    constructor(mBaseUrl: String, mPath: String, mMethod: EnmRealRequestMethod) {
        this.mBaseUrl = mBaseUrl
        this.mPath = mPath
        this.mMethod = mMethod
    }

    constructor(mBaseUrl: String, mPath: String, mBody: ByteArray, mMethod: EnmRealRequestMethod, mHeaders: Map<String, String>?) {
        this.mBaseUrl = mBaseUrl
        this.mPath = mPath
        this.mBody = mBody
        this.mMethod = mMethod
        this.mHeaders = mHeaders
    }

    object RequestHeader{
        val HEADER_KEY_CONTENT_TYPE = "Content-Type"
        val HEADER_KEY_SESSION_ID = "SessionId"
    }

    class Builder{
        private lateinit var mBaseUrl: String
        private lateinit var mPath: String
        private lateinit var mBody: ByteArray
        private lateinit var mMethod: EnmRealRequestMethod
        private          var mHeaders: MutableMap<String, String>? = mutableMapOf(RequestHeader.HEADER_KEY_CONTENT_TYPE to "application/json; charset=UTF-8")

        fun baseUrl(baseUrl: String): Builder {
            mBaseUrl = baseUrl
            return this
        }

        fun path(path: String): Builder {
            mPath = path
            return this
        }

        fun method(method: EnmRealRequestMethod): Builder {
            mMethod = method
            return this
        }

        fun headers(headers: MutableMap<String, String>?): Builder {
            mHeaders = headers
            return this
        }

        fun addHeader(pair: Pair<String, String>?): Builder {
            if (pair == null){
                return this
            }

            if (mHeaders == null){
                mHeaders = mutableMapOf(pair)
                return this
            }

            mHeaders?.put(pair.first, pair.second)
            return this
        }

        fun params(params: BasePackageOut?): Builder {
            val json: String? = JsonTools.toJson(params)
            if (json != null){
                mBody = json.toByteArray()
            }
            return this
        }

        fun build(): RealRequest = RealRequest(mBaseUrl, mPath, mBody, mMethod, mHeaders)
    }
}