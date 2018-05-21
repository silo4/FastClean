package com.zhonglongzhou.fastclean.presentation.autotool

import java.io.File

/**
 * Created by zhonglz on 2018/5/10
 */

//fun main(args: Array<String>){
////    GoGoGo().test("hello world")
//    println("hello world")
//}

class GoGoGo{
    var name: String = "MyTest"
    val REPLACEMENT = "Template__"
    val CURRENT_PACKAGE = "autotool"

    /**
     * @param name, 名称如HelloActivity,则输入Hello
     * @param uiActivity, true表示是Activity, false表示是fragment
     */
    fun go(name: String, uiActivity : Boolean){
        this.name = name
        //获取目标目录
        val destDir = destDir()
        //创建目录
        generateDir(destDir)
        //读取各模板文件并替换
        val templateDir = currentDir()
        println()
        println()
        println("---------------------------------- intent -----------------------------")
        var original = readTemplateFile(templateDir + File.separator + "Template__Intent.kt")
        var fresh = replaceTemplateName(original, name)
        generateFile(fresh, name + "Intent.kt")
        println()
        println()

        println("---------------------------------- action -----------------------------")
        original = readTemplateFile(templateDir + File.separator + "Template__Action.kt")
        fresh = replaceTemplateName(original, name)
        generateFile(fresh, name + "Action.kt")
        println()
        println()

        println("---------------------------------- result -----------------------------")
        original = readTemplateFile(templateDir + File.separator + "Template__Result.kt")
        fresh = replaceTemplateName(original, name)
        generateFile(fresh, name + "Result.kt")
        println()
        println()

        println("---------------------------------- view state -----------------------------")
        original = readTemplateFile(templateDir + File.separator + "Template__ViewState.kt")
        fresh = replaceTemplateName(original, name)
        generateFile(fresh, name + "ViewState.kt")
        println()
        println()

        println("---------------------------------- view model -----------------------------")
        original = readTemplateFile(templateDir + File.separator + "Template__ViewModel.kt")
        fresh = replaceTemplateName(original, name)
        generateFile(fresh, name + "ViewModel.kt")
        println()
        println()

        println("---------------------------------- processor -----------------------------")
        original = readTemplateFile(templateDir + File.separator + "Template__Processor.kt")
        fresh = replaceTemplateName(original, name)
        generateFile(fresh, name + "Processor.kt")
        println()
        println()

        when(uiActivity){
            true -> {
                println("---------------------------------- Activity -----------------------------")
                original = readTemplateFile(templateDir + File.separator + "Template__Activity.kt")
                fresh = replaceTemplateName(original, name)
                generateFile(fresh, name + "Activity.kt")
                println()
                println()
            }

            false -> {
                println("---------------------------------- Fragment -----------------------------")
                original = readTemplateFile(templateDir + File.separator + "Template__Fragment.kt")
                fresh = replaceTemplateName(original, name)
                generateFile(fresh, name + "Fragment.kt")
                println()
                println()
            }
        }
    }

    private fun parentDir() : String{
        val curDir = System.getProperty("user.dir")
        val pkgName = "com\\zhonglongzhou\\fastclean"

        return String.format("%s\\src\\main\\java\\%s\\presentation", curDir, pkgName)
    }

    private fun currentDir() : String = parentDir() + File.separator + CURRENT_PACKAGE
    private fun destDir() : String = parentDir() + "\\${name.toLowerCase()}"

    /**
     * 创建目录
     */
    private fun generateDir(dstDir: String) : String{
        println("============= creating directory... [$dstDir]")
        val file = File(dstDir)
        if (file.exists()){
            throw Exception("this directory has existed [$dstDir]...")
        }
        val ret = file.mkdir()
        if (!ret){
            throw Exception("make directory failed [$dstDir]...")
        }
        println("============= create directory succeed. [$dstDir]")
        return dstDir
    }

    /**
     * 读取模板文件
     */
    private fun readTemplateFile(filePath: String) : String{
        println("============= reading template file... [$filePath]")
        val file = File(filePath)
        val content = file.readText()
        println("============= read template file end... [$filePath]")
        return content
    }

    /**
     * 替换目标名称
     */
    private fun replaceTemplateName(content: String, target: String) : String {
        println("============= replacing template name... [$REPLACEMENT -> $target]")
        val newContent =  content.replace(REPLACEMENT, target)
        println("============= replace template name end... [$REPLACEMENT -> $target]")

        println("============= replacing package name... [$CURRENT_PACKAGE -> $target]")
        val newContent1 =  newContent.replace(CURRENT_PACKAGE, target.toLowerCase())
        println("============= replace package name end... [$CURRENT_PACKAGE -> $target]")
        return newContent1
    }

    /**
     * 替换包名
     */
    private fun replacePackageName(content: String, target: String) : String{
        println("============= replacing package name... [$CURRENT_PACKAGE -> $target]")
        val newContent =  content.replace(CURRENT_PACKAGE, target)
        println("============= replace package name end... [$CURRENT_PACKAGE -> $target]")
        return newContent
    }

    /**
     * 生成文件
     */
    private fun generateFile(content: String, fileName: String) {
        val destFilePath = destDir() + File.separator + fileName
        println("============= generating target file... [$destFilePath]")
        val file = File(destFilePath)
//        file.appendText(content)
//        val writer = file.writer()
//        val outputStream = file.outputStream()
//        file.bufferedWriter().use {out ->
//            out.write(content)
//        }
        file.writeText(content)

        println("============= generate target file end... [$destFilePath]")
    }
}