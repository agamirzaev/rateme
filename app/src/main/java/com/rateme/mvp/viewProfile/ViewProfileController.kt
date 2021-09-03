package com.rateme.mvp.bookmarks

import com.rateme.data.model.ResponseToken
import com.rateme.data.model.bookMarks.ResponseBookMarks
import com.rateme.data.model.follower.Subscriptions
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.data.model.viewProfile.ResponseViewProfile
import com.rateme.mvp.MvpView

interface ViewProfileController {
    interface View : MvpView {
        fun getViewProfile(responseViewProfile: ResponseViewProfile)
    }

    interface Presenter : MvpView {
        fun responseViewProfile(user_id: String, session_id: String)
    }
}