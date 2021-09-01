package com.rateme.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ResponseToken {
    @SerializedName("id_user")
    @Expose
    private var idUser: String? = null

    @SerializedName("token")
    @Expose
    private var token: String? = null

    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getStatus(): Boolean? {
        return status
    }

    fun getIdUser(): String? {
        return idUser
    }

    fun getToken(): String? {
        return token
    }

    fun getMessage(): String? {
        return message
    }
}