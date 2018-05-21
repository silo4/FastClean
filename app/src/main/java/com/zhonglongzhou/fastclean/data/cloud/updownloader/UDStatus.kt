package com.zhonglongzhou.fastclean.data.cloud.updownloader

/**
 * Created by zhonglz on 2018/3/6
 */

open class UDStatus(var udSize : Long = 0L,
                    var totalSize: Long = 0L){
    constructor(status: UDStatus) : this(status.udSize, status.totalSize)

    fun percent(): Double{
        return if (totalSize == 0L){
            0.0
        }else{
            udSize * 1.0 / totalSize
        }
    }
}

class UDNormal(status: UDStatus) : UDStatus(status)

class UDSuspend(status: UDStatus) : UDStatus(status)

class UDWaiting(status: UDStatus) : UDStatus(status)

class UDLoading(status: UDStatus) : UDStatus(status)

class UDFailed(status: UDStatus, val throwable: Throwable) : UDStatus(status)

class UDSucceed(status: UDStatus) : UDStatus(status)

class UDDeleted(status: UDStatus) : UDStatus(status)