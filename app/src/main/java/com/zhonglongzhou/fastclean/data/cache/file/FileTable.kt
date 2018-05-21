package com.zhonglongzhou.fastclean.data.cache.file

import android.content.Context
import androidx.content.edit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhonglz on 2018/2/23
 * 文件缓存相关信息的表
 * 映射关系 (key, value) = (http url, local path)
 */
@Singleton
class FileTable @Inject constructor(private val context: Context){
    private val FILE_CACHE_TABLE_PREFS = "file_cache_table_prefs"

    private val prefs by lazy {
        context.applicationContext.getSharedPreferences(FILE_CACHE_TABLE_PREFS, Context.MODE_PRIVATE)
    }

    fun updateItem(url: String, path: String?){
        prefs.edit {
            putString(url, path)
        }
    }

    fun getItem(url: String): String?{
        return prefs.getString(url, "")
    }

    fun removeItem(url: String){
        prefs.edit{
            remove(url)
        }
    }
}