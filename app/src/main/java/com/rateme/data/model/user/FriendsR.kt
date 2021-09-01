package com.rateme.data.model.user

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class RequestFMessage {
    @SerializedName("recipient")
    @Expose
    var recipient: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("request_f")
    @Expose
    var requestF: Int? = null
}