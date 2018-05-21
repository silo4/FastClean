package com.zhonglongzhou.fastclean.data.entity.businessA.mapper

import com.zhonglongzhou.fastclean.data.entity.businessA.BusinessAEntity
import com.zhonglongzhou.fastclean.data.entity.businessB.response.GetBusinessBResponse
import com.zhonglongzhou.fastclean.domain.businessA.bean.BusinessA
import com.zhonglongzhou.fastclean.domain.businessA.bean.packageIn.GetBusinessAPI
import com.zhonglongzhou.fastclean.domain.businessB.bean.BusinessB
import com.zhonglongzhou.fastclean.domain.businessB.bean.packageIn.GetBusinessBPI
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhonglz on 2018/5/8
 *
 * transform
 * response ----> packageIn
 * bean ----> entity
 */

@Singleton
class AEntityDataMapper @Inject constructor(){
    fun transform(entity: BusinessAEntity?) : GetBusinessAPI {
        val what = entity?.what?:""
        return GetBusinessAPI(BusinessA(what))
    }
}