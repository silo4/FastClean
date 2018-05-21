package com.zhonglongzhou.fastclean.data.cloud.businessB

import com.zhonglongzhou.fastclean.data.cloud.businessB.config.BusinessBConstant
import com.zhonglongzhou.fastclean.data.cloud.httpkit.EnmRealRequestMethod
import com.zhonglongzhou.fastclean.data.cloud.httpkit.RealRequest
import com.zhonglongzhou.fastclean.data.cloud.httpkit.RequestTemplate
import com.zhonglongzhou.fastclean.domain.businessB.bean.packageOut.GetBusinessBPO

/**
 * Created by zhonglz on 2018/5/8
 */

class BusinessBRequestFactory private constructor(){
    companion object {

        fun buildGetBusinessBRequest(getBusinessBPO: GetBusinessBPO) : RequestTemplate{
            val realRequest = RealRequest.Builder()
                    .baseUrl(BusinessBConstant.BASE_URL)
                    .method(EnmRealRequestMethod.GET)
                    .path(BusinessBConstant.Path.BUSINESS_B)
                    .params(getBusinessBPO)
                    .build()
            return RequestTemplate(realRequest)
        }
    }
}