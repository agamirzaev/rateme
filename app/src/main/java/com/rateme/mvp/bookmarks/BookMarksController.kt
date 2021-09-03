package com.rateme.mvp.bookmarks

import com.rateme.data.model.ResponseToken
import com.rateme.data.model.bookMarks.ResponseBookMarks
import com.rateme.data.model.follower.Subscriptions
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.mvp.MvpView

interface BookMarksController {
    interface View : MvpView {
        fun getBookMarks(bookMarks: ResponseBookMarks)
    }

    interface Presenter : MvpView {
        fun responseBookMarks(user_id: String, session_id: String)
    }
}