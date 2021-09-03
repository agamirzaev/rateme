package com.rateme.mvp.loadDataProfileUser

import com.rateme.data.model.bookMarks.ResponseBookMarks
import com.rateme.data.model.user.ResponseUser
import com.rateme.mvp.MvpView

interface LoadDataProfileUserController {
    interface View : MvpView {
        fun loadProfileUser(user: ResponseUser)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseProfileUser(user_id: String, session_id: String)
    }
}