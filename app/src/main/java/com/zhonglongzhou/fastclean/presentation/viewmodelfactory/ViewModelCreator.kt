package com.zhonglongzhou.fastclean.presentation.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.zhonglongzhou.fastclean.data.cache.businessA.BusinessACacheImpl
import com.zhonglongzhou.fastclean.data.cloud.businessB.BusinessBCloudApiImpl
import com.zhonglongzhou.fastclean.data.entity.businessA.mapper.AEntityDataMapper
import com.zhonglongzhou.fastclean.data.entity.businessB.mapper.BEntityDataMapper
import com.zhonglongzhou.fastclean.data.repository.businessA.ARepositoryImpl
import com.zhonglongzhou.fastclean.data.repository.businessA.datasource.AStoreFactory
import com.zhonglongzhou.fastclean.data.repository.businessB.BRepositoryImpl
import com.zhonglongzhou.fastclean.data.repository.businessB.datasource.BStoreFactory
import com.zhonglongzhou.fastclean.presentation.mvibase.schedulers.SchedulerProvider
import com.zhonglongzhou.fastclean.presentation.test.TestProcessor
import com.zhonglongzhou.fastclean.presentation.test.TestViewModel

/**
 * Created by zhonglz on 2018/2/11
 */

class ViewModelCreator(private val applicationContext: Context) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            //example
//            HomeViewModel::class.java -> HomeViewModel(HomeProcessorHolder(SchedulerProvider, applicationContext)) as T

            TestViewModel::class.java -> TestViewModel(TestProcessor(ARepositoryImpl(applicationContext, AStoreFactory(applicationContext, BusinessACacheImpl(applicationContext)), AEntityDataMapper()),
                                                                        BRepositoryImpl(applicationContext, BStoreFactory(applicationContext, BusinessBCloudApiImpl(applicationContext)), BEntityDataMapper()),
                                                                        SchedulerProvider,
                                                                        applicationContext)) as T

            Nothing::class.java -> throw IllegalArgumentException("unknown model class $modelClass")

            else -> throw IllegalArgumentException("unknown model class $modelClass")
        }
    }

}