package com.zhonglongzhou.fastclean.data.cache.businessA

import android.content.Context
import com.zhonglongzhou.fastclean.data.cache.SharedPrefCache
import com.zhonglongzhou.fastclean.data.cache.file.FileCacheImpl
import com.zhonglongzhou.fastclean.data.entity.businessA.BusinessAEntity
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhonglz on 2018/5/8
 */

@Singleton
class BusinessACacheImpl @Inject constructor(private val context: Context): BusinessACache{

    private val fileCache by lazy {
        FileCacheImpl(context)
    }

    private val prefs by lazy {
        SharedPrefCache(context)
    }

    override fun getBusinessA(): Observable<BusinessAEntity> {
        val entity = prefs.getBussinessA()

        return Observable.just(entity)
    }

    override fun updateBusinessA(entity: BusinessAEntity?) : Observable<Boolean> {
        return Observable.just(prefs.updateBusinessA(entity))
    }
}