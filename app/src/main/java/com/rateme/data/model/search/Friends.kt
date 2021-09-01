package com.rateme.data.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Friends {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("session_id")
    @Expose
    private var session_id: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("request_friends")
    @Expose
    private var request_friends: String? = null

    fun getId(): String? {
        return id
    }
    fun getSessionId(): String? {
        return session_id
    }

    fun getStatus(): String? {
        return status
    }

    fun getRequestFriends(): String? {
        return request_friends
    }
}