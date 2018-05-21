package com.zhonglongzhou.fastclean.presentation.autotool

import android.content.Context
import com.zhonglongzhou.fastclean.presentation.mvibase.schedulers.BaseSchedulerProvider
import com.zhonglongzhou.fastclean.presentation.utility.SchedulerUtil
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import org.jetbrains.anko.AnkoLogger

/**
 * automatically generated
 */

class Template__Processor(private  val schedulerProvider: BaseSchedulerProvider, private val context: Context) {
    companion object {
        val logger = AnkoLogger<Template__Processor>()
    }

    private val initialProcessor = ObservableTransformer<Template__Action.InitialAction, Template__Result.InitialResult>{
        actions ->
        actions.map { act -> Template__Result.InitialResult(act.action)}
                .compose(SchedulerUtil.schedulerIoUi(schedulerProvider))
    }

    private val finishProcessor = ObservableTransformer<Template__Action.FinishAction, Template__Result.FinishResult>{
        actions ->
        actions.map { act -> Template__Result.FinishResult(act.action) }
                .compose(SchedulerUtil.schedulerIoUi(schedulerProvider))
    }

    val actionProcessor = ObservableTransformer<Template__Action, Template__Result>{
        actions ->
        actions.publish {shared ->
            Observable.merge(
                    shared.ofType(Template__Action.InitialAction::class.java).compose(initialProcessor),
                    shared.ofType(Template__Action.FinishAction::class.java).compose(finishProcessor))
                    .mergeWith(
                            shared.filter{ v ->
                                v !is Template__Action.InitialAction
                                && v !is Template__Action.FinishAction
                            }.flatMap { w -> Observable.error<Template__Result>(IllegalArgumentException("Unknown Action type: $w")) }
                    )
        }
    }
}