package com.zhonglongzhou.fastclean.presentation.utility

import com.zhonglongzhou.fastclean.presentation.mvibase.schedulers.BaseSchedulerProvider
import io.reactivex.ObservableTransformer


/**
 * Created by zhonglz on 2018/1/2
 */

object SchedulerUtil{

    fun <T> schedulerIoUi(schedulerProvider: BaseSchedulerProvider): ObservableTransformer<T, T> {
        return ObservableTransformer{ observable ->
            observable.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
        }
    }
}