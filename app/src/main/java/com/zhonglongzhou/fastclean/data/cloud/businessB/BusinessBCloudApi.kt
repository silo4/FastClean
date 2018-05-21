package com.zhonglongzhou.fastclean.data.cloud.businessB

import com.zhonglongzhou.fastclean.data.entity.businessB.response.GetBusinessBResponse
import com.zhonglongzhou.fastclean.domain.businessB.bean.packageOut.GetBusinessBPO
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/8
 */

interface BusinessBCloudApi{
    fun getBusinessB(getBusinessBPO: GetBusinessBPO): Observable<GetBusinessBResponse>
}