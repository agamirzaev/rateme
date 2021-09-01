package com.rateme.mvp.loginUser

import com.rateme.data.model.ResponseToken
import com.rateme.mvp.MvpView

interface LoginUserController {
    interface View : MvpView {
        fun loginUser(responseToken: ResponseToken)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseTokenUser(phone: String, password: String)
    }
}