package com.zhonglongzhou.fastclean.data.repository.businessB.datasource

import com.zhonglongzhou.fastclean.data.entity.businessB.BusinessBEntity
import com.zhonglongzhou.fastclean.domain.businessB.bean.packageOut.GetBusinessBPO
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/9
 */

interface BStore{
    fun getBusinessB(params: GetBusinessBPO) : Observable<BusinessBEntity>
}