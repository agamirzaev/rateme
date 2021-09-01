package com.rateme.data.model.search

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Users {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("avatar")
    @Expose
    private var avatar: String? = null

    @SerializedName("username")
    @Expose
    private var username: String? = null

    @SerializedName("rate")
    @Expose
    private var rate: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getAvatar(): String? {
        return avatar
    }

    fun setAvatar(avatar: String?) {
        this.avatar = avatar
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getRate(): String? {
        return rate
    }

    fun setRate(rate: String?) {
        this.rate = rate
    }
}