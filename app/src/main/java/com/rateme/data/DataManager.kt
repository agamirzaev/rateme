package com.rateme.data

import com.rateme.data.model.ResponseToken
import com.rateme.data.model.follower.Subscriptions
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.data.model.search.ResponseSearchPeople
import com.rateme.data.model.user.ResponseUser
import com.rateme.data.server.Api
import com.rateme.data.server.ServicesGenerator
import retrofit2.Call

class DataManager {
    private val api = ServicesGenerator.createService(Api::class.java)

    fun getLoginUser(phone: String, password: String): Call<ResponseToken> {
        return api.loginUser(phone, password)
    }

    fun getRegUser(
        phone: String,
        name: String,
        password: String,
        username: String
    ): Call<ResponseToken> {
        return api.registrationUser(phone, name, password, username)
    }

    fun getLoadDataMyProfile(token: String, user_id: String): Call<UserResponse> {
        return api.profileData(token, user_id)
    }

    fun getLoadDataProfileUser(user_id: String, session_id: String): Call<ResponseUser> {
        return api.profileDataUser(user_id, session_id)
    }

    fun getSearchPeople(search: String, session_id: String): Call<ResponseSearchPeople> {
        return api.getSearchPeople(search, session_id)
    }

    fun getSubscriptions(
        session_id: String,
        friends_id: String,
        request_f: String
    ): Call<Subscriptions> {
        return api.getSubscriptions(session_id, friends_id, request_f)
    }
}