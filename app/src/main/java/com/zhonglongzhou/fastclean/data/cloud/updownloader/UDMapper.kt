package com.zhonglongzhou.fastclean.data.cloud.updownloader

import zlc.season.rxdownload3.core.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhonglz on 2018/3/6
 */

@Singleton
class UDMapper @Inject constructor(){

    fun transform(status: Status) : UDStatus {
        return UDStatus(status.downloadSize, status.totalSize)
    }

    fun transform(status: Normal) : UDNormal {
        return UDNormal(transform(status))
    }

    fun transform(status: Suspend) : UDSuspend {
        return UDSuspend(transform(status))
    }

    fun transform(status: Waiting) : UDWaiting {
        return UDWaiting(transform(status))
    }

    fun transform(status: Downloading) : UDLoading {
        return UDLoading(transform(status))
    }

    fun transform(status: Failed) : UDFailed {
        return UDFailed(transform(status), status.throwable)
    }

    fun transform(status: Succeed) : UDSucceed {
        return UDSucceed(transform(status))
    }

    fun transform(status: Deleted) : UDDeleted {
        return UDDeleted(transform(status))
    }


    fun transform(udMission: UDMission) : Mission{
        return Mission(udMission.url, udMission.saveName, udMission.path)
    }
}