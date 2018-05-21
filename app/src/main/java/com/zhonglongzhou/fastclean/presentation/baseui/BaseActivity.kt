package com.zhonglongzhou.fastclean.presentation.baseui

import android.support.v4.app.FragmentActivity
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import com.zhonglongzhou.fastclean.R
import org.jetbrains.anko.find

/**
 * Created by zhonglz on 2017/11/30
 */

abstract class BaseActivity : FragmentActivity(){


    //////////////////////////////// loading view //////////////////////////////
    private var progressView: ImageView? = null
    private var loadingView: View? = null
    private var acceptEventView: List<View>? = null
    private var shieldPhysicalKey = true//是否屏蔽物理键

    fun showLoadingView(){
        if (loadingView == null){
            loadingView = LayoutInflater.from(this).inflate(R.layout.loading_view, null)
        }
        progressView = loadingView?.find(R.id.ivLoading)
        val loadingAnim = AnimationUtils.loadAnimation(this, R.anim.anim_loading)
        loadingAnim.interpolator = LinearInterpolator()
        progressView?.startAnimation(loadingAnim)

        if (loadingView?.parent == null){
            val lp = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            lp.gravity = Gravity.CENTER
            getBaseRootView().addView(loadingView, lp)
        }
    }

    private fun showSpecialLoadingView(shieldPhysicalKey: Boolean, vararg acceptEventView : View){
        showLoadingView()
        this.acceptEventView = acceptEventView.toList()
        this.shieldPhysicalKey = shieldPhysicalKey
    }

    fun dismissLoadingView(){
        if (loadingView != null){
            getBaseRootView().removeView(loadingView)
            this.acceptEventView = null
            this.shieldPhysicalKey = true
        }
    }

    private fun getBaseRootView() : FrameLayout = find(android.R.id.content)

    private fun isLoadingShow() : Boolean = (loadingView != null && loadingView?.parent != null)

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (this.shieldPhysicalKey && isLoadingShow()){
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        //屏蔽多点触摸
        if (ev?.pointerCount!! > 1 && isLoadingShow()){
            return true
        }
        if (this.acceptEventView != null && acceptEventView!!.isNotEmpty()){
            this.acceptEventView!!
                    .filter { isPointInView(it, ev.rawX.toInt(), ev.rawY.toInt()) }
                    .forEach { return super.dispatchTouchEvent(ev) }
        }
        if (isLoadingShow()){
            return true
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun isPointInView(view : View?, x: Int, y: Int) : Boolean{
        if (view == null){
            return false
        }
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val left = location[0]
        val top = location[1]
        val right = left + view.measuredWidth
        val bottom = top + view.measuredHeight

        return (x in left..right) && (y in top..bottom)
    }

}
