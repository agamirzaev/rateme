package com.rateme.data.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseSearchPeople {
    @SerializedName("users")
    @Expose
    private var users: ArrayList<Users>? = null

    fun getUsers(): ArrayList<Users>? {
        return users
    }

    @SerializedName("friends")
    @Expose
    private var friends: ArrayList<Friends>? = null

    fun getFriends(): ArrayList<Friends>? {
        return friends
    }

    @SerializedName("session")
    @Expose
    private var session: ArrayList<FriendsRequest>? = null

    fun getSession(): ArrayList<FriendsRequest>? {
        return session
    }
}