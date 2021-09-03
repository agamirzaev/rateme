package com.rateme.data.model.updateUsername

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseUpdateUsername {
    @SerializedName("status")
    @Expose
    private var status: String? = ""

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }
}