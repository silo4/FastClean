package com.zhonglongzhou.fastclean.presentation.test

import com.zhonglongzhou.fastclean.presentation.mvibase.MviViewState

/**
 * automatically generated
 */

open class TestViewState : MviViewState {
    data class InitialViewState(val state: Nothing?) : TestViewState()
    data class FinishViewState(val state: Nothing?) : TestViewState()
    data class BusinessAViewState(val what: String) : TestViewState()
    data class BusinessBViewState(val what: String) : TestViewState()
}