package com.zhonglongzhou.fastclean.data.entity.businessB.mapper

import com.google.gson.reflect.TypeToken
import com.zhonglongzhou.fastclean.data.entity.businessB.response.GetBusinessBResponse
import com.zhonglongzhou.fastclean.data.tools.JsonTools
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhonglz on 2018/5/8
 */

@Singleton
class BEntityJsonMapper @Inject private constructor(){

    fun transformGetBusinessBResponse(json: String?) : GetBusinessBResponse?{
        val type = object : TypeToken<GetBusinessBResponse>(){}.type
        return JsonTools.fromJson<GetBusinessBResponse>(json, type)
    }
}