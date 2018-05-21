package com.zhonglongzhou.fastclean.presentation.application

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.zhonglongzhou.fastclean.data.cache.file.FileDirectory
import zlc.season.rxdownload3.core.DownloadConfig

/**
 * Created by zhonglz on 2018/1/19
 */

class TheApp : MultiDexApplication() {

    companion object {
        lateinit var instance: TheApp
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        init()
    }

    private fun init(){
        initRxDownload()
    }

    private fun initRxDownload(){
        val builder = DownloadConfig.Builder.create(this)
                .setFps(20)
                .setDefaultPath(FileDirectory.get(this, FileDirectory.EnmDir.DEFAULT))
                .enableDb(false)
                .enableAutoStart(false)
                .enableNotification(false)

        DownloadConfig.init(builder)
    }

}