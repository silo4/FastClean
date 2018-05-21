package com.zhonglongzhou.fastclean.data.cache.file

import android.content.Context
import com.ailianlian.bicycle.data.cache.file.FileCache
import java.io.File
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhonglz on 2018/2/23
 */

@Singleton
class FileCacheImpl @Inject constructor(private val context: Context): FileCache {

    private val fileTable by lazy {
        FileTable(context)
    }

    override fun cache(url: String, path: String, inputStream: InputStream): Long {
        //写入文件
        val size = File(path).copyInputStreamToFile(inputStream)
        //更新表
        if (size > 0){
            fileTable.updateItem(url, path)
        }

        return size
    }

    override fun cache(url: String, path: String): Long {
        //写入文件
        val size = File(path).length()
        //更新表
        if (size > 0){
            fileTable.updateItem(url, path)
        }

        return size
    }

    override fun exist(url: String?): Boolean {

        url?.let {
            val path = fileTable.getItem(url)
            return path?.let {
                File(path).exists()
            } ?: false
        } ?: return false
    }

    override fun getCachePath(url: String): String? {
        return fileTable.getItem(url)
    }

    override fun evict(url: String) {
        if (!exist(url)){
            return
        }
        val path = getCachePath(url)
        File(path).delete()
        fileTable.removeItem(url)
    }

    override fun evictAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun File.copyInputStreamToFile(inputStream: InputStream): Long{
        inputStream.use { input ->
            this.outputStream().use { fileOut ->
                return input.copyTo(fileOut)
            }
        }
    }

}