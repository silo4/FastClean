package com.zhonglongzhou.fastclean.presentation.mvibase.schedulers

import io.reactivex.Scheduler

/**
 * Created by zhonglz on 2018/5/4
 */

interface BaseSchedulerProvider{
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler

}