package com.zhonglongzhou.fastclean.domain.businessB.repository

import com.zhonglongzhou.fastclean.domain.businessB.bean.packageIn.GetBusinessBPI
import com.zhonglongzhou.fastclean.domain.businessB.bean.packageOut.GetBusinessBPO
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/4
 */

interface BRepository{
    fun getBusinessB(params: GetBusinessBPO): Observable<GetBusinessBPI>
}