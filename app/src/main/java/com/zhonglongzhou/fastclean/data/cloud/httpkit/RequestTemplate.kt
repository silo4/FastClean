package com.zhonglongzhou.fastclean.data.cloud.httpkit

import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.util.FuelRouting
import com.google.gson.reflect.TypeToken
import com.zhonglongzhou.fastclean.data.tools.JsonTools
import java.nio.charset.Charset

/**
 * Created by zhonglz on 2018/5/8
 */

open class RequestTemplate (val realRequest: RealRequest): FuelRouting{

    override val basePath: String
        get() = realRequest.mBaseUrl

    override val headers: Map<String, String>?
        get() = realRequest.mHeaders

    override val method: Method
        get() = when(realRequest.mMethod){
            EnmRealRequestMethod.GET -> Method.GET
            EnmRealRequestMethod.POST -> Method.POST
            EnmRealRequestMethod.PUT -> Method.PUT
            EnmRealRequestMethod.DELETE -> Method.DELETE
            EnmRealRequestMethod.PATCH -> Method.PATCH
            EnmRealRequestMethod.HEAD -> Method.HEAD
        }

    override val params: List<Pair<String, Any?>>?
        get() = realRequest.mBody.let {
            val type = object : TypeToken<Map<String, Any?>>(){}.type
            val strJson = realRequest.mBody.toString(Charset.defaultCharset())
            val jsonObj = JsonTools.fromJson< Map<String, Any?> >(strJson, type)
            val pairList = mutableListOf<Pair<String, Any?>>()
            for (entry in jsonObj){
                pairList.add(entry.toPair())
            }
            pairList
        }

    override val path: String
        get() = realRequest.mPath

}