package com.rateme.mvp.searchPeople

import com.rateme.data.model.search.Friends
import com.rateme.data.model.search.Users
import com.rateme.mvp.MvpView

interface SearchPeopleController {
    interface View : MvpView {
        fun searchPeople(searchPeople: ArrayList<Users>?, friends: ArrayList<Friends>?)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun notSearchUsers()
    }

    interface Presenter : MvpView {
        fun responseSearchPeople(search: String, session_id: String)
    }
}