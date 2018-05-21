package com.zhonglongzhou.fastclean.presentation.baseui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction


/**
 * Created by zhonglz on 2017/11/30
 */

abstract class BaseFragment : Fragment(){

    fun replaceFragment(fragment: Fragment, tag: String, name: String){
        fragmentManager?.beginTransaction()
                ?.replace(android.R.id.content, fragment, tag)
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.addToBackStack(name)
                ?.commitAllowingStateLoss()
    }

    fun addFragment(fragment: Fragment, tag: String, name: String){
        fragmentManager?.beginTransaction()
                ?.add(android.R.id.content, fragment, tag)
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.addToBackStack(name)
                ?.commitAllowingStateLoss()
    }

    fun clearAllFragment(){
        fragmentManager?.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun popFragment(){
        fragmentManager?.popBackStack()
    }

    fun showLoadingView(){
        if (activity != null){
            (activity as BaseActivity).showLoadingView()
        }
    }

    fun dismissLoadingView(){
        if (activity != null){
            (activity as BaseActivity).dismissLoadingView()
        }
    }
}