package com.rateme.mvp

interface MvpPresenter  <V : MvpView?> {
    fun attachView(view: V)
    fun detachView()
}