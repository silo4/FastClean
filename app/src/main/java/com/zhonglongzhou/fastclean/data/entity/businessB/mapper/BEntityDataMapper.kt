package com.zhonglongzhou.fastclean.data.entity.businessB.mapper

import com.zhonglongzhou.fastclean.data.entity.businessB.BusinessBEntity
import com.zhonglongzhou.fastclean.data.entity.businessB.response.GetBusinessBResponse
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
class BEntityDataMapper @Inject constructor(){
    fun transform(response: GetBusinessBResponse?) : GetBusinessBPI {
        val what = response?.data?.business?.what ?: ""
        return GetBusinessBPI(BusinessB(what))
    }

    fun transform(entity: BusinessBEntity?) : GetBusinessBPI {
        val what = entity?.what ?: ""
        return GetBusinessBPI(BusinessB(what))
    }
}