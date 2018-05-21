package com.zhonglongzhou.fastclean.presentation.autotool

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhonglongzhou.fastclean.R
import com.zhonglongzhou.fastclean.presentation.baseui.BaseFragment
import com.zhonglongzhou.fastclean.presentation.mvibase.MviView
import com.zhonglongzhou.fastclean.presentation.viewmodelfactory.ViewModelCreator
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.support.v4.toast

/**
 * automatically generated
 */

class Template__Fragment : BaseFragment(), MviView<Template__Intent, Template__ViewState> {

    private lateinit var contentView : View
    private var viewModel: Template__ViewModel? = null
    private var disposables: CompositeDisposable? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contentView = inflater.inflate(R.layout.fragment_template__, container, false)
        initView()
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposables = CompositeDisposable()

        viewModel = ViewModelProviders.of(this, ViewModelCreator(activity?.applicationContext!!))
                .get(Template__ViewModel::class.java)

        viewModel?.states()?.subscribe(this::render)?.let { disposables?.add(it) }

        viewModel?.processIntents(intents())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables?.dispose()
        viewModel = null
        disposables = null
    }

    private fun initView(){

    }

    private fun initialIntent() : Observable<Template__Intent.InitialIntent>{
        return Observable.just(Template__Intent.InitialIntent(null))
    }

    private fun finishIntent() : Observable<Template__Intent.FinishIntent>{
        return Observable.just(Template__Intent.FinishIntent(null))
    }


    override fun intents(): Observable<Template__Intent> {
        return Observable.merge(initialIntent(), finishIntent())
    }

    override fun render(state: Template__ViewState) {
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

}