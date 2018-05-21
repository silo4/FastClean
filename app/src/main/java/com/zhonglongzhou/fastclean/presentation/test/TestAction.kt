package com.zhonglongzhou.fastclean.presentation.test

import com.zhonglongzhou.fastclean.presentation.mvibase.MviAction

/**
 * automatically generated
 */

interface TestAction : MviAction {
    data class InitialAction(val action: Nothing?): TestAction
    data class FinishAction(val action: Nothing?): TestAction
    data class BusinessAAction(val what: String): TestAction
    data class BusinessBAction(val what: String): TestAction
}
