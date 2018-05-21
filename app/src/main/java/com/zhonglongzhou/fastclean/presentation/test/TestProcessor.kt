package com.zhonglongzhou.fastclean.presentation.test

import android.content.Context
import com.zhonglongzhou.fastclean.domain.businessA.bean.packageOut.GetBusinessAPO
import com.zhonglongzhou.fastclean.domain.businessA.repository.ARepository
import com.zhonglongzhou.fastclean.domain.businessB.bean.packageOut.GetBusinessBPO
import com.zhonglongzhou.fastclean.domain.businessB.repository.BRepository
import com.zhonglongzhou.fastclean.presentation.mvibase.schedulers.BaseSchedulerProvider
import com.zhonglongzhou.fastclean.presentation.utility.SchedulerUtil
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import org.jetbrains.anko.AnkoLogger

/**
 * automatically generated
 */

class TestProcessor(private val aRepository: ARepository, private val bRepository: BRepository, private val schedulerProvider: BaseSchedulerProvider, private val context: Context) {
    companion object {
        val logger = AnkoLogger<TestProcessor>()
    }

    private val initialProcessor = ObservableTransformer<TestAction.InitialAction, TestResult.InitialResult>{
        actions ->
        actions.map { act -> TestResult.InitialResult(act.action)}
                .compose(SchedulerUtil.schedulerIoUi(schedulerProvider))
    }

    private val finishProcessor = ObservableTransformer<TestAction.FinishAction, TestResult.FinishResult>{
        actions ->
        actions.map { act -> TestResult.FinishResult(act.action) }
                .compose(SchedulerUtil.schedulerIoUi(schedulerProvider))
    }

    private val aProcessor = ObservableTransformer<TestAction.BusinessAAction, TestResult.BusinessAResult>{
        actions ->
        actions.flatMap { act ->
            aRepository.getBusinessA(GetBusinessAPO(1))
                    .map { pi -> TestResult.BusinessAResult(pi.business.what) }
                    .onErrorReturn { _ -> TestResult.BusinessAResult("error A") }
                    .compose(SchedulerUtil.schedulerIoUi(schedulerProvider))
        }
    }

    private val bProcessor = ObservableTransformer<TestAction.BusinessBAction, TestResult.BusinessBResult>{
        actions ->
        actions.flatMap { act ->
            bRepository.getBusinessB(GetBusinessBPO(1))
                    .map { pi -> TestResult.BusinessBResult(pi.business.what) }
                    .onErrorReturn { _ -> TestResult.BusinessBResult("error B") }
                    .compose(SchedulerUtil.schedulerIoUi(schedulerProvider))
        }
    }

    val actionProcessor = ObservableTransformer<TestAction, TestResult>{
        actions ->
        actions.publish {shared ->
            Observable.merge(
                    shared.ofType(TestAction.InitialAction::class.java).compose(initialProcessor),
                    shared.ofType(TestAction.FinishAction::class.java).compose(finishProcessor),
                    shared.ofType(TestAction.BusinessAAction::class.java).compose(aProcessor),
                    shared.ofType(TestAction.BusinessBAction::class.java).compose(bProcessor))
                    .mergeWith(
                            shared.filter{ v ->
                                v !is TestAction.InitialAction
                                && v !is TestAction.FinishAction
                                && v !is TestAction.BusinessAAction
                                && v !is TestAction.BusinessBAction
                            }.flatMap { w -> Observable.error<TestResult>(IllegalArgumentException("Unknown Action type: $w")) }
                    )
        }
    }
}