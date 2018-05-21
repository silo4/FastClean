package com.zhonglongzhou.fastclean.data.repository.businessA.datasource

import com.zhonglongzhou.fastclean.data.cache.businessA.BusinessACache
import com.zhonglongzhou.fastclean.data.entity.businessA.BusinessAEntity
import com.zhonglongzhou.fastclean.domain.businessA.bean.packageOut.GetBusinessAPO
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/9
 */

class CloudAStore : AStore{

    override fun getBusinessA(params: GetBusinessAPO): Observable<BusinessAEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateBusinessA(what: String): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
