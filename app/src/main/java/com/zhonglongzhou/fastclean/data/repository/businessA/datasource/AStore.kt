package com.zhonglongzhou.fastclean.data.repository.businessA.datasource

import com.zhonglongzhou.fastclean.data.entity.businessA.BusinessAEntity
import com.zhonglongzhou.fastclean.domain.businessA.bean.packageOut.GetBusinessAPO
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/8
 */

interface AStore{
    fun getBusinessA(params: GetBusinessAPO) : Observable<BusinessAEntity>
    fun updateBusinessA(what: String) : Observable<Boolean>
}