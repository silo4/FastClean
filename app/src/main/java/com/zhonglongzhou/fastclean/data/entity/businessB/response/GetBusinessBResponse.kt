package com.zhonglongzhou.fastclean.data.entity.businessB.response

import com.google.gson.annotations.SerializedName
import com.zhonglongzhou.fastclean.data.entity.base.BaseData
import com.zhonglongzhou.fastclean.data.entity.base.BaseResponse
import com.zhonglongzhou.fastclean.data.entity.businessB.BusinessBEntity

/**
 * Created by zhonglz on 2018/5/8
 */

class GetBusinessBResponse : BaseResponse<GetBusinessBResponse.Data>(){
    data class Data(@SerializedName("business") val business: BusinessBEntity?) : BaseData()
}
