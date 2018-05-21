package com.zhonglongzhou.fastclean.data.cloud.httpkit

import com.zhonglongzhou.fastclean.data.cloud.httpkit.exception.BaseNetException
import com.zhonglongzhou.fastclean.data.cloud.httpkit.exception.ExternalNetException
import com.zhonglongzhou.fastclean.data.cloud.httpkit.exception.JsonParseNetException
import com.zhonglongzhou.fastclean.data.cloud.httpkit.exception.NetErrorData
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.zhonglongzhou.fastclean.data.tools.JsonTools
import java.lang.reflect.Type


/**
 * Created by zhonglz on 2018/5/8
 * 网络请求库，使用 Fuel
 * https://github.com/kittinunf/Fuel
 */

object HttpKit{
    private val mFuelManager: FuelManager by lazy {
        FuelManager()
    }

    fun <T> doRequest(request: RequestTemplate, type: Type) : Pair<T?, BaseNetException?>{
        println("do request thread ${Thread.currentThread().name} : ${Thread.currentThread().id} ")
        val (_, response, result) = mFuelManager.request(request).body(request.realRequest.mBody).responseJson()
        println("response: $response")
        val (data, error) = result
        return if (error == null){
            val content = data?.content
            println("content: $content")
            try {
                val t = JsonTools.fromJson<T>(content, type)
                Pair(t, null)
            }catch (e: Exception){
                println("json parse failed [${e.message}]")
                Pair(null, JsonParseNetException(e.message, e.cause))
            }
        }else{
            Pair(null, ExternalNetException(NetErrorData(error.response.statusCode, error.message) ,error.message, error.cause))
        }
    }

    fun  doRequestImage(request: RequestTemplate) : Pair<ResponseTemplate?, BaseNetException?>{
        println("do request thread ${Thread.currentThread().name} : ${Thread.currentThread().id} ")
        val (_, response, result) = mFuelManager.request(request).body(request.realRequest.mBody).responseJson()
        println("response: $response")
        val (_, error) = result
        return if (error == null){
            Pair(ResponseTemplate(response), null)
        }else{
            Pair(null, ExternalNetException(NetErrorData(error.response.statusCode, error.message) ,error.message, error.cause))
        }
    }

}