package com.zhonglongzhou.fastclean.domain.businessA.repository

import com.zhonglongzhou.fastclean.domain.businessA.bean.packageIn.GetBusinessAPI
import com.zhonglongzhou.fastclean.domain.businessA.bean.packageOut.GetBusinessAPO
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/4
 */

interface ARepository{
    fun getBusinessA(params: GetBusinessAPO) : Observable<GetBusinessAPI>
}