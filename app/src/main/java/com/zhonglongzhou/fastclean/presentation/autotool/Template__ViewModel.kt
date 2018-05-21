package com.zhonglongzhou.fastclean.presentation.autotool

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

class Template__ViewModel : MviViewModel<Template__Intent, Template__ViewState>, ViewModel {
    private val intentsSubject : PublishSubject<Template__Intent>
    private val statesObservable : Observable<Template__ViewState>
    private val actionProcessor : Template__Processor

    constructor(actionProcessor: Template__Processor) : super() {
        this.actionProcessor = actionProcessor

        intentsSubject = PublishSubject.create()
        statesObservable = compose()
    }

    private fun compose() : Observable<Template__ViewState>{
        return intentsSubject
                .compose(intentFilter)
                .map(this::actionFromIntent)
                .compose(actionProcessor.actionProcessor)
                .scan(Template__ViewState.InitialViewState(null), reducer)
                .replay(1)
                .autoConnect(0)
    }

    private val intentFilter = ObservableTransformer<Template__Intent, Template__Intent>{
        upstream ->
        upstream.publish { shared ->
            Observable.merge(shared.ofType<Template__Intent.InitialIntent>(Template__Intent.InitialIntent::class.java).take(1),
                    shared.filter { intent -> intent !is Template__Intent.InitialIntent })
        }
    }

    private val reducer = BiFunction<Template__ViewState, Template__Result, Template__ViewState>{
        previousState, result ->
        when(result){
            is Template__Result.InitialResult -> Template__ViewState.InitialViewState(result.result)
            is Template__Result.FinishResult -> Template__ViewState.FinishViewState(result.result)

            else -> throw IllegalStateException("Mishandled result? Should not happen (as always): $result")
        }
    }

    private fun actionFromIntent(intent: MviIntent) : Template__Action = when(intent){
        is Template__Intent.InitialIntent -> Template__Action.InitialAction(intent.intent)
        is Template__Intent.FinishIntent -> Template__Action.FinishAction(intent.intent)
        else -> throw IllegalArgumentException("don't know how to treat this intent $intent")
    }

    override fun processIntents(intents: Observable<Template__Intent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<Template__ViewState> {
        return statesObservable
    }
}