package com.rateme.data.model.user

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ResponseUser {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("username")
    @Expose
    private var username: String? = null

    @SerializedName("avatar")
    @Expose
    private var avatar: String? = null

    @SerializedName("first_last_name")
    @Expose
    private var firstLastName: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("phone")
    @Expose
    private var phone: String? = null

    @SerializedName("sex")
    @Expose
    private var sex: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("about")
    @Expose
    private var about: String? = null

    @SerializedName("rate")
    @Expose
    private var rate: String? = null

    @SerializedName("account")
    @Expose
    private var account: String? = null

    @SerializedName("post")
    @Expose
    private var post: Int? = null

    @SerializedName("followers")
    @Expose
    private var followers: Int? = null

    @SerializedName("following")
    @Expose
    private var following: Int? = null

    @SerializedName("request_me_join")
    @Expose
    private val requestMeJoin: Int? = null

    @SerializedName("request_user_join")
    @Expose
    private val requestUserJoin: Int? = null

    @SerializedName("bookmarks")
    @Expose
    private val bookmarks: Int? = null

    fun getBookMarks(): Int? {
        return bookmarks
    }

    fun getRequestMeJoin(): Int? {
        return requestMeJoin
    }

    fun getRequestUserJoin(): Int? {
        return requestUserJoin
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getAvatar(): String? {
        return avatar
    }

    fun setAvatar(avatar: String?) {
        this.avatar = avatar
    }

    fun getFirstLastName(): String? {
        return firstLastName
    }

    fun setFirstLastName(firstLastName: String?) {
        this.firstLastName = firstLastName
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getSex(): String? {
        return sex
    }

    fun setSex(sex: String?) {
        this.sex = sex
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getAbout(): String? {
        return about
    }

    fun setAbout(about: String?) {
        this.about = about
    }

    fun getRate(): String? {
        return rate
    }

    fun setRate(rate: String?) {
        this.rate = rate
    }

    fun getAccount(): String? {
        return account
    }

    fun setAccount(account: String?) {
        this.account = account
    }

    fun getPost(): Int? {
        return post
    }

    fun setPost(post: Int?) {
        this.post = post
    }

    fun getFollowers(): Int? {
        return followers
    }

    fun setFollowers(followers: Int?) {
        this.followers = followers
    }

    fun getFollowing(): Int? {
        return following
    }

    fun setFollowing(following: Int?) {
        this.following = following
    }
}