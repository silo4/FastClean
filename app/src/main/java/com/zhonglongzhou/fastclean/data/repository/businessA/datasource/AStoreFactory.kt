package com.zhonglongzhou.fastclean.data.repository.businessA.datasource

import android.content.Context
import com.zhonglongzhou.fastclean.data.cache.businessA.BusinessACache
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhonglz on 2018/5/9
 */
@Singleton
class AStoreFactory @Inject constructor(private val context: Context, private val cache: BusinessACache)  {

    fun createCloudStore() : AStore{
        return CloudAStore()
    }

    fun createLocalStore() : AStore{
        return LocalAStore(cache)
    }
}