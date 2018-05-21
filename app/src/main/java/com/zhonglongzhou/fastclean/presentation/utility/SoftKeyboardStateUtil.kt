package com.zhonglongzhou.fastclean.presentation.utility

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver

/**
 * Created by zhonglz on 2018/1/16
 */

class SoftKeyboardStateUtil : ViewTreeObserver.OnGlobalLayoutListener {

    private val activityRootView: View
    private var lastSoftKeyboardHeightInPx: Int = 0
    private var isSoftKeyboardOpened: Boolean
    private var stateListener: ((Boolean) -> Unit)? = null

    constructor(rootView: View, isOpened: Boolean) {
        this.activityRootView = rootView
        this.isSoftKeyboardOpened = isOpened
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    fun setStateListener(listener: (Boolean) -> Unit) {
        this.stateListener = listener
    }

    private fun notifyListener(isOpened: Boolean) {
        stateListener?.invoke(isOpened)
    }

    override fun onGlobalLayout() {
        val rect = Rect()
        activityRootView.getWindowVisibleDisplayFrame(rect)

        val heightDiff = activityRootView.rootView.height.minus((rect.bottom - rect.top))
        if (!isSoftKeyboardOpened && heightDiff > activityRootView.rootView.height.div(3)) {
            isSoftKeyboardOpened = true
            lastSoftKeyboardHeightInPx = heightDiff
            notifyListener(isSoftKeyboardOpened)
        } else if (isSoftKeyboardOpened && heightDiff < activityRootView.rootView.height.div(3)) {
            isSoftKeyboardOpened = false
            notifyListener(isSoftKeyboardOpened)
        }


    }

}