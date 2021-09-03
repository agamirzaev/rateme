package com.rateme.data.server

import com.rateme.data.model.ResponseToken
import com.rateme.data.model.bookMarks.ResponseBookMarks
import com.rateme.data.model.follower.Subscriptions
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.data.model.search.ResponseSearchPeople
import com.rateme.data.model.updateUsername.ResponseUpdateUsername
import com.rateme.data.model.user.ResponseUser
import com.rateme.data.model.viewProfile.ResponseViewProfile
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("oauth/login.php")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseToken>

    @FormUrlEncoded
    @POST("oauth/reg.php")
    fun registrationUser(
        @Field("phone") phone: String,
        @Field("first_last_name") name: String,
        @Field("password") password: String,
        @Field("username") username: String
    ): Call<ResponseToken>

    @GET("profile/profile_data.php")
    fun profileData(
        @Query("token") token: String,
        @Query("user_id") user_id: String
    ): Call<UserResponse>

    @GET("users/profile.php")
    fun profileDataUser(
        @Query("friends_id") user_id: String,
        @Query("session_id") session_id: String
    ): Call<ResponseUser>

    @GET("users/search.php")
    fun getSearchPeople(
        @Query("search") search: String,
        @Query("session_id") session_id: String
    ): Call<ResponseSearchPeople>

    @FormUrlEncoded
    @POST("v1/add_join.php")
    fun getSubscriptions(
        @Field("session_id") session_id: String,
        @Field("friends_id") friends_id: String,
        @Field("status") request_f: String
    ): Call<Subscriptions>

    @FormUrlEncoded
    @POST("v1/add_bookmarks.php")
    fun getBookMarks(
        @Field("session_id") session_id: String,
        @Field("user_id") user_id: String
    ): Call<ResponseBookMarks>

    @FormUrlEncoded
    @POST("v1/view_profile.php")
    fun getViewProfile(
        @Field("session_id") session_id: String,
        @Field("user_id") user_id: String
    ): Call<ResponseViewProfile>

    @FormUrlEncoded
    @POST("profile/update_username.php")
    fun getUpdateUsername(
        @Field("session_id") session_id: String,
        @Field("username") username: String
    ): Call<ResponseUpdateUsername>
}