package com.rateme.mvp.loadDataMyProfile

import android.util.Log
import com.rateme.data.DataManager
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.mvp.BasePresenter
import com.rateme.mvp.loadDataProfileUser.LoadDataProfileUserController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadDataMyProfilePresenter(private val dataManager: DataManager) :
    BasePresenter<LoadDataMyProfileController.View>(),
    LoadDataMyProfileController.Presenter {

    private lateinit var call: Call<UserResponse>

    override fun responseMyProfile(token: String, user_id: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getLoadDataMyProfile(token, user_id)
            call.enqueue(object : Callback<UserResponse> {

                override fun onResponse(
                    call: Call<UserResponse>,
                    loginResponse: Response<UserResponse>
                ) {
                    it?.hideProgress()
                    if (loginResponse.isSuccessful) {
                        loginResponse.body()?.let { _ ->
                            it?.loadMyProfile(loginResponse.body()!!)
                        }
                    }
                    Log.e("LoadDataMyProfile", call.request().toString())
                }

                override fun onFailure(
                    call: Call<UserResponse>,
                    t: Throwable
                ) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("ErrorLoadDataMyProfile", t.message.toString())
                }
            })
        }
    }
}