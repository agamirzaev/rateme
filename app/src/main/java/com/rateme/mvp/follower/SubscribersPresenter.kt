package com.rateme.mvp.follower

import android.util.Log
import com.rateme.data.DataManager
import com.rateme.data.model.follower.Subscriptions
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubscribersPresenter(private val dataManager: DataManager) :
    BasePresenter<SubscribersController.View>(),
    SubscribersController.Presenter {

    private lateinit var call: Call<Subscriptions>

    override fun responseSubscriptions(session_id: String, friends_id: String, request_f: String) {
        mvpView.let { it ->
            call = dataManager.getSubscriptions(session_id, friends_id, request_f)
            call.enqueue(object : Callback<Subscriptions> {

                override fun onResponse(
                    call: Call<Subscriptions>,
                    loginResponse: Response<Subscriptions>
                ) {
                    if (loginResponse.isSuccessful) {
                        loginResponse.body()?.let { _ ->
                            it?.getSubscriptions(loginResponse.body()!!)
                        }
                    }
                    Log.e("Subscriptions", call.request().toString())
                }

                override fun onFailure(
                    call: Call<Subscriptions>,
                    t: Throwable
                ) {
                    Log.e("ErrorSubscriptions", t.message.toString())
                }
            })
        }
    }
}