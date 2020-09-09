package com.lingxiao.kotlin.study.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author luckw
 * @date   2020/9/3
 */
open class BasePresenter<T : IBaseView> : IPresenter<T> {
    var mRootView: T? = null
        private set

    private var mCompositeDisposable = CompositeDisposable()

    override fun attachView(rootView: T) {
        this.mRootView = rootView
    }

    override fun detachView() {
        mRootView = null
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.clear()
        }
    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    fun checkViewAttached() {
        if (!isViewAttached) {
            throw ViewNotFoundExeception()
        }
    }

    fun addSubscription(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }


    private class ViewNotFoundExeception internal constructor()
        : RuntimeException("Please call IPresenter.attachView(IBaseView) before requesting data to the IPresenter")
}