package com.rateme.data.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FriendsRequest {
    @SerializedName("user_id")
    @Expose
    private var userId: String? = null

    @SerializedName("session_id")
    @Expose
    private var sessionId: String? = null

    @SerializedName("request_friends")
    @Expose
    private var requestFriends: String? = null


    @SerializedName("status")
    @Expose
    private var status: String? = null

    fun getStatus(): String? {
        return status
    }

    fun getUserId(): String? {
        return userId
    }

    fun getSessionId(): String? {
        return sessionId
    }

    fun getRequestFriends(): String? {
        return requestFriends
    }

}