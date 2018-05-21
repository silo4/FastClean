package com.zhonglongzhou.fastclean.presentation.utility

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by zhonglz on 2018/1/9
 */

object InputMethodUtil{
    fun showSoftKeyboard(view: View){
        if (view.requestFocus()){
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideSoftKeyboard(view: View){
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}