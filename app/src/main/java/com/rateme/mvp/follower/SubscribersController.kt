package com.rateme.mvp.follower

import com.rateme.data.model.ResponseToken
import com.rateme.data.model.follower.Subscriptions
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.mvp.MvpView

interface SubscribersController {
    interface View : MvpView {
        fun getSubscriptions(subscriptions: Subscriptions)
    }

    interface Presenter : MvpView {
        fun responseSubscriptions(session_id: String, friends_id: String, request_f: String)
    }
}