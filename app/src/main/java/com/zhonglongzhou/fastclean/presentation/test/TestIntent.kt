package com.zhonglongzhou.fastclean.presentation.test

import com.zhonglongzhou.fastclean.presentation.mvibase.MviIntent

/**
 * automatically generated
 */

interface TestIntent : MviIntent {
    data class InitialIntent(val intent: Nothing?) : TestIntent
    data class FinishIntent(val intent: Nothing?) : TestIntent

    data class BusinessAIntent(val what: String) : TestIntent
    data class BusinessBIntent(val what: String) : TestIntent
}