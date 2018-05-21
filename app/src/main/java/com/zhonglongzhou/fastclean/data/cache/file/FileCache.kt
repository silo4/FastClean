package com.ailianlian.bicycle.data.cache.file

import java.io.InputStream

/**
 * Created by zhonglz on 2018/2/23
 */

interface FileCache{
    fun cache(url: String, path: String, inputStream: InputStream): Long
    fun cache(url: String, path: String): Long
    fun exist(url: String?) : Boolean
    fun getCachePath(url: String): String?
    fun evict(url: String)
    fun evictAll()
}