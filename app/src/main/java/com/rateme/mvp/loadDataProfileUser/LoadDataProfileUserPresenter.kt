package com.rateme.mvp.loadDataProfileUser

import android.util.Log
import com.rateme.data.DataManager
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.data.model.user.ResponseUser
import com.rateme.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadDataProfileUserPresenter(private val dataManager: DataManager) :
    BasePresenter<LoadDataProfileUserController.View>(),
    LoadDataProfileUserController.Presenter {

    private lateinit var call: Call<ResponseUser>

    override fun responseProfileUser(user_id: String, session_id: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getLoadDataProfileUser(user_id, session_id)
            call.enqueue(object : Callback<ResponseUser> {

                override fun onResponse(
                    call: Call<ResponseUser>,
                    loginResponse: Response<ResponseUser>
                ) {
                    it?.hideProgress()
                    if (loginResponse.isSuccessful) {
                        loginResponse.body()?.let { _ ->
                            it?.loadProfileUser(loginResponse.body()!!)
                        }
                    }
                    Log.e("LoadDataMyProfile", call.request().toString())
                }

                override fun onFailure(
                    call: Call<ResponseUser>,
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