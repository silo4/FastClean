package com.zhonglongzhou.fastclean.presentation.autotool

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.zhonglongzhou.fastclean.R
import com.zhonglongzhou.fastclean.presentation.baseui.BaseActivity
import com.zhonglongzhou.fastclean.presentation.mvibase.MviView
import com.zhonglongzhou.fastclean.presentation.viewmodelfactory.ViewModelCreator
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

/**
 * automatically generated
 */

class Template__Activity : BaseActivity(), MviView<Template__Intent, Template__ViewState> {

    companion object {
        val logger = AnkoLogger<Template__Activity>()
    }

    private var disposables: CompositeDisposable? = null
    private var viewModel: Template__ViewModel? = null

    private val finishIntentSubject = PublishSubject.create<Template__Intent.FinishIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template__)

        disposables = CompositeDisposable()

        viewModel = ViewModelProviders.of(this, ViewModelCreator(applicationContext))
                .get(Template__ViewModel::class.java)

        viewModel?.states()?.subscribe(this::render)?.let { disposables?.add(it) }

        viewModel?.processIntents(intents())

    }

    override fun onDestroy() {
        super.onDestroy()

        disposables?.dispose()
        disposables = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishIntentSubject.onNext(Template__Intent.FinishIntent(null))
    }

    override fun intents(): Observable<Template__Intent> {
        return Observable.merge(initialIntent(), finishIntentSubject)
    }

    override fun render(state: Template__ViewState) {
        logger.info("view state $state")
        when(state){

            is Template__ViewState.InitialViewState -> {
                toast("initial view state: ${state.state}")
                TODO("deal with ui")
            }
            is Template__ViewState.FinishViewState ->{
                toast("finish view state $state")
                TODO("deal with ui")
            }

            else -> {
                toast("error view state $state")
            }
        }
    }

    private fun initialIntent() : Observable<Template__Intent.InitialIntent> {
        return Observable.just(Template__Intent.InitialIntent(null))
    }

}