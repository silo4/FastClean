package com.zhonglongzhou.fastclean.data.repository.businessB.datasource

import android.content.Context
import com.zhonglongzhou.fastclean.data.cache.businessA.BusinessACache
import com.zhonglongzhou.fastclean.data.cloud.businessB.BusinessBCloudApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhonglz on 2018/5/9
 */
@Singleton
class BStoreFactory @Inject constructor(private val context: Context, private val api: BusinessBCloudApi)  {

    fun createCloudStore() : BStore{
        return CloudBStore(api)
    }

    fun createLocalStore() : BStore{
        return LocalBStore()
    }
}