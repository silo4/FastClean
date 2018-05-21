package com.zhonglongzhou.fastclean.presentation.test

import com.zhonglongzhou.fastclean.presentation.mvibase.MviResult

/**
 * automatically generated
 */

interface TestResult : MviResult {
    data class InitialResult(val result: Nothing?) : TestResult
    data class FinishResult(val result: Nothing?) : TestResult
    data class BusinessAResult(val what: String): TestResult
    data class BusinessBResult(val what: String): TestResult
}