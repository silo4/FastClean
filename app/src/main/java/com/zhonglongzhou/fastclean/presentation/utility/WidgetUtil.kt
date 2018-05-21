package com.zhonglongzhou.fastclean.presentation.utility

import android.widget.EditText
import android.widget.TextView

/**
 * Created by zhonglz on 2017/12/5
 */

object WidgetUtil {

    fun setEditTextCursor(editText: EditText, drawableId: Int){
        try {
            val f = TextView::class.java.getDeclaredField("mCursorDrawableRes")
            f.isAccessible = true
            f.set(editText, drawableId)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }



}