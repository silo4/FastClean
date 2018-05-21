package com.zhonglongzhou.fastclean.data.repository.businessA.datasource

import com.zhonglongzhou.fastclean.data.cache.businessA.BusinessACache
import com.zhonglongzhou.fastclean.data.entity.businessA.BusinessAEntity
import com.zhonglongzhou.fastclean.domain.businessA.bean.packageOut.GetBusinessAPO
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/9
 */

class LocalAStore(private val cache: BusinessACache) : AStore{

    override fun getBusinessA(params: GetBusinessAPO): Observable<BusinessAEntity> {
        return cache.getBusinessA()
    }

    override fun updateBusinessA(what: String): Observable<Boolean> {
        return cache.updateBusinessA(BusinessAEntity(what))
    }

}