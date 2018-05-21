package com.zhonglongzhou.fastclean.data.repository.businessA

import android.content.Context
import com.zhonglongzhou.fastclean.data.entity.businessA.mapper.AEntityDataMapper
import com.zhonglongzhou.fastclean.data.entity.businessB.mapper.BEntityDataMapper
import com.zhonglongzhou.fastclean.data.repository.businessA.datasource.AStoreFactory
import com.zhonglongzhou.fastclean.domain.businessA.bean.packageIn.GetBusinessAPI
import com.zhonglongzhou.fastclean.domain.businessA.bean.packageOut.GetBusinessAPO
import com.zhonglongzhou.fastclean.domain.businessA.repository.ARepository
import io.reactivex.Observable

/**
 * Created by zhonglz on 2018/5/8
 */

class ARepositoryImpl (private val context: Context,
                       private val storeFactory: AStoreFactory,
                       private val dataMapper: AEntityDataMapper) : ARepository {

    override fun getBusinessA(params: GetBusinessAPO): Observable<GetBusinessAPI> {
        return storeFactory.createLocalStore().getBusinessA(params).map(dataMapper::transform)
    }

}