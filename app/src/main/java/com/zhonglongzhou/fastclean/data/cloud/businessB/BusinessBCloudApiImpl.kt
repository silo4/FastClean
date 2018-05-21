package com.zhonglongzhou.fastclean.data.cloud.businessB

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.zhonglongzhou.fastclean.data.cloud.httpkit.HttpKit
import com.zhonglongzhou.fastclean.data.cloud.httpkit.RequestTemplate
import com.zhonglongzhou.fastclean.data.cloud.httpkit.exception.*
import com.zhonglongzhou.fastclean.data.entity.base.BaseResponse
import com.zhonglongzhou.fastclean.data.entity.base.ImageResponse
import com.zhonglongzhou.fastclean.data.entity.businessB.response.GetBusinessBResponse
import com.zhonglongzhou.fastclean.data.tools.ConnectionTools
import com.zhonglongzhou.fastclean.domain.businessB.bean.packageOut.GetBusinessBPO
import io.reactivex.Observable
import java.lang.reflect.Type

/**
 * Created by zhonglz on 2018/5/8
 */

class BusinessBCloudApiImpl ( private val context: Context ) : BusinessBCloudApi{

    override fun getBusinessB(getBusinessBPO: GetBusinessBPO): Observable<GetBusinessBResponse> {
        val requestTemplate = BusinessBRequestFactory.buildGetBusinessBRequest(getBusinessBPO)
        return doRequest(requestTemplate, object : TypeToken<GetBusinessBResponse>() {}.type)
    }


    private fun <T> doRequest(requestTemplate: RequestTemplate, type: Type) : Observable<T> {
        return Observable.create { emitter ->
            if (ConnectionTools.isThereInternetConnection(context)) {
                val (response, exception) = HttpKit.doRequest<T>(requestTemplate, type)
                when {
                    exception != null -> emitter.onError(exception)
                    response == null -> emitter.onError(ResponseEmptyNetException("返回为空"))
                    else -> {
                        val success = (response as BaseResponse<*>).success
                        success?.let {
                            if (success){
                                emitter.onNext(response)
                                emitter.onComplete()
                            }else{
                                emitter.onError(InternalNetException(NetErrorData(response.statusCode, response.message)))
                            }

                        } ?: emitter.onError(UnknownNetException("未知错误"))
                    }
                }

            } else {
                emitter.onError(NoConnectionNetException("无网络连接"))
            }
        }
    }

    private inline fun<reified T : ImageResponse> doRequestImage(requestTemplate: RequestTemplate  ) : Observable<T> {
        return Observable.create { emitter ->
            if (ConnectionTools.isThereInternetConnection(context)) {
                val (responseTemplate, exception) = HttpKit.doRequestImage(requestTemplate)
                when {
                    exception != null -> emitter.onError(exception)
                    responseTemplate == null -> emitter.onError(ResponseEmptyNetException("返回为空"))
                    else -> {
                        val response = responseTemplate.response
                        val resp = T::class.java.newInstance()
                        resp.success = true
                        resp.data = response.data
                        resp.statusCode = response.statusCode
                        resp.message = response.responseMessage

                        emitter.onNext(resp)
                        emitter.onComplete()
                    }
                }

            } else {
                emitter.onError(NoConnectionNetException("无网络连接"))
            }
        }
    }
}