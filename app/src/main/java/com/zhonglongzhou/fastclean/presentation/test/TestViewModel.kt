package com.zhonglongzhou.fastclean.presentation.test

import android.arch.lifecycle.ViewModel
import com.zhonglongzhou.fastclean.presentation.mvibase.MviIntent
import com.zhonglongzhou.fastclean.presentation.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject

/**
 * automatically generated
 */

class TestViewModel : MviViewModel<TestIntent, TestViewState>, ViewModel {
    private val intentsSubject : PublishSubject<TestIntent>
    private val statesObservable : Observable<TestViewState>
    private val actionProcessor : TestProcessor

    constructor(actionProcessor: TestProcessor) : super() {
        this.actionProcessor = actionProcessor

        intentsSubject = PublishSubject.create()
        statesObservable = compose()
    }

    private fun compose() : Observable<TestViewState>{
        return intentsSubject
                .compose(intentFilter)
                .map(this::actionFromIntent)
                .compose(actionProcessor.actionProcessor)
                .scan(TestViewState.InitialViewState(null), reducer)
                .replay(1)
                .autoConnect(0)
    }

    private val intentFilter = ObservableTransformer<TestIntent, TestIntent>{
        upstream ->
        upstream.publish { shared ->
            Observable.merge(shared.ofType<TestIntent.InitialIntent>(TestIntent.InitialIntent::class.java).take(1),
                    shared.filter { intent -> intent !is TestIntent.InitialIntent })
        }
    }

    private val reducer = BiFunction<TestViewState, TestResult, TestViewState>{
        previousState, result ->
        when(result){
            is TestResult.InitialResult -> TestViewState.InitialViewState(result.result)
            is TestResult.FinishResult -> TestViewState.FinishViewState(result.result)
            is TestResult.BusinessAResult -> TestViewState.BusinessAViewState(result.what)
            is TestResult.BusinessBResult -> TestViewState.BusinessBViewState(result.what)

            else -> throw IllegalStateException("Mishandled result? Should not happen (as always): $result")
        }
    }

    private fun actionFromIntent(intent: MviIntent) : TestAction = when(intent){
        is TestIntent.InitialIntent -> TestAction.InitialAction(intent.intent)
        is TestIntent.FinishIntent -> TestAction.FinishAction(intent.intent)
        is TestIntent.BusinessAIntent -> TestAction.BusinessAAction(intent.what)
        is TestIntent.BusinessBIntent -> TestAction.BusinessBAction(intent.what)
        else -> throw IllegalArgumentException("don't know how to treat this intent $intent")
    }

    override fun processIntents(intents: Observable<TestIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<TestViewState> {
        return statesObservable
    }
}