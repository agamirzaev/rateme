package com.rateme.mvp.loadDataMyProfile

import com.rateme.data.model.ResponseToken
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.mvp.MvpView

interface LoadDataMyProfileController {
    interface View : MvpView {
        fun loadMyProfile(user: UserResponse)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseMyProfile(token: String, user_id: String)
    }
}