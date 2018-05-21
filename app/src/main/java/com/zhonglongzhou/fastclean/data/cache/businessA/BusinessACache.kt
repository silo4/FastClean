package com.zhonglongzhou.fastclean.data.cache.businessA

import com.zhonglongzhou.fastclean.data.entity.businessA.BusinessAEntity
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/8
 */

interface BusinessACache{
    fun getBusinessA() : Observable<BusinessAEntity>
    fun updateBusinessA(entity: BusinessAEntity?) : Observable<Boolean>
}