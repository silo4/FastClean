package com.zhonglongzhou.fastclean.data.cache.file

import android.content.Context
import android.os.Environment
import java.io.File
import java.net.URLConnection
import java.util.*

/**
 * Created by zhonglz on 2018/2/23
 */

object FileDirectory {

    private val scannerDirList = mutableListOf<String>()

    enum class EnmDir(val dir: String) {
        PHOTOS("photos"),
        VERSIONS("versions"),
        UPLOAD("upload"),
        DEFAULT("default"),
    }

    fun get(context: Context, type: EnmDir): String{
        val file = File(rootCacheDir(context), type.dir)
        file.mkdirs()

        return file.absolutePath
    }

    private fun rootCacheDir(context: Context): File {
        return firstCacheDir(context) ?: secondCacheDir(context) ?: thirdCacheDir(context)
    }

    /**
     * 通过系统api getExternalCacheDir()获取存储卡路径,有可能返回失败
     * 这个做为第一个获取路径的方法
     * https://my.oschina.net/liucundong/blog/288183
     */
    private fun firstCacheDir(context: Context): File? {
        return context.externalCacheDir
    }

    /**
     * 通过遍历出来的路径列表来获取存储卡路径
     * 这个做为第二个获取路径的方法
     * dir/Android/com.package.name/cache
     */
    private fun secondCacheDir(context: Context): File?{
        val dirList = scanExternalDirList(context)
        val subDir = "Android" + File.separator + context.packageName + File.separator + "cache"
        for (dir in dirList){
            val cache = File(dir, subDir)
            if (cache.exists()){
                return cache
            }
            if (cache.mkdirs()){
                return cache
            }
        }
        return null
    }

    /**
     * 通过系统api getCacheDir()获取存储路径
     * 这个做为第三个选择
     */
    private fun thirdCacheDir(context: Context): File {
        return context.cacheDir
    }

    /**
     * 遍历 /system/etc/vold.fstab 获取到存储卡路径列表
     * http://bbs.csdn.net/topics/391891314?page=1
     */
    private fun scanExternalDirList(context: Context): List<String>{
        if (scannerDirList.isNotEmpty()){
            return scannerDirList
        }

        try {
            val scannerFile = "/system/etc/vold.fstab"
            val scanner = Scanner(File(scannerFile))
            while (scanner.hasNext()){
                val line = scanner.nextLine()
                if (line.startsWith("dev_mount")){
                    val lineElements = line.split(" ")
                    var element = lineElements[2]

                    if (element.contains(":")){
                        element = element.substring(0, element.indexOf(":"))
                    }
                    if (element.contains("usb")){
                        continue
                    }
                    if (element == Environment.getExternalStorageDirectory().path){
                        continue
                    }
                    scannerDirList.add(element)
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        return scannerDirList
    }

    /**
     * glide 处理 .jpg图片有些问题
     * https://stackoverflow.com/questions/35123738/loading-images-using-glide-in-imageview
     * 所以默认都保存为.png
     */
    fun buildImageNameFromUrl(url: String, prefix: String): String {
        val format = URLConnection.guessContentTypeFromName(url)
        format?.let {
            val suffix = when(format.toLowerCase()){
                "image/jpg",
                "image/jpeg" -> ".jpg"
                "image/png" -> ".png"
                "image/gif" -> ".gif"
                "image/bmp" -> ".bmp"
                else -> ".jpg"
            }
            return prefix + suffix
        } ?: return ".jpg"
    }
}