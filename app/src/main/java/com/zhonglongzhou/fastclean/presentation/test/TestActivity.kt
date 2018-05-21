package com.zhonglongzhou.fastclean.presentation.test

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import com.zhonglongzhou.fastclean.R
import com.zhonglongzhou.fastclean.presentation.baseui.BaseActivity
import com.zhonglongzhou.fastclean.presentation.mvibase.MviView
import com.zhonglongzhou.fastclean.presentation.viewmodelfactory.ViewModelCreator
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_test.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

/**
 * automatically generated
 */

class TestActivity : BaseActivity(), MviView<TestIntent, TestViewState> {

    companion object {
        val logger = AnkoLogger<TestActivity>()
    }

    private var disposables: CompositeDisposable? = null
    private var viewModel: TestViewModel? = null

    private val finishIntentSubject = PublishSubject.create<TestIntent.FinishIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        disposables = CompositeDisposable()

        viewModel = ViewModelProviders.of(this, ViewModelCreator(applicationContext))
                .get(TestViewModel::class.java)

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
        finishIntentSubject.onNext(TestIntent.FinishIntent(null))
    }

    override fun intents(): Observable<TestIntent> {
        return Observable.merge(initialIntent(), finishIntentSubject, AIntent(), BIntent())
    }

    override fun render(state: TestViewState) {
        logger.info("view state $state")
        when(state){

            is TestViewState.InitialViewState -> {
                toast("initial view state: ${state.state}")
            }
            is TestViewState.FinishViewState ->{
                toast("finish view state $state")
            }

            is TestViewState.BusinessAViewState -> {
                toast(" A ? (${state.what})")
            }

            is TestViewState.BusinessBViewState -> {
                toast(" B ? (${state.what})")
            }

            else -> {
                toast("error view state $state")
            }
        }
    }

    private fun initialIntent() : Observable<TestIntent.InitialIntent> {
        return Observable.just(TestIntent.InitialIntent(null))
    }

    private fun AIntent() : Observable<TestIntent.BusinessAIntent> {
        return RxView.clicks(businessA).map { _ -> TestIntent.BusinessAIntent("AAAAA") }
    }

    private fun BIntent() : Observable<TestIntent.BusinessBIntent> {
        return RxView.clicks(businessB).map { _ -> TestIntent.BusinessBIntent("BBBBB") }
    }
}