package com.zhonglongzhou.fastclean.data.repository.businessB

import android.content.Context
import com.zhonglongzhou.fastclean.data.entity.businessB.mapper.BEntityDataMapper
import com.zhonglongzhou.fastclean.data.repository.businessB.datasource.BStoreFactory
import com.zhonglongzhou.fastclean.domain.businessB.bean.packageIn.GetBusinessBPI
import com.zhonglongzhou.fastclean.domain.businessB.bean.packageOut.GetBusinessBPO
import com.zhonglongzhou.fastclean.domain.businessB.repository.BRepository
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/8
 */

class BRepositoryImpl (private val context: Context,
                       private val storeFactory: BStoreFactory,
                       private val dataMapper: BEntityDataMapper): BRepository {

    override fun getBusinessB(params: GetBusinessBPO): Observable<GetBusinessBPI> {
        return storeFactory.createCloudStore().getBusinessB(params).map(dataMapper::transform)
    }

}