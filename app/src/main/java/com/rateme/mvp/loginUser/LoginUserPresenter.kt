package com.rateme.mvp.loginUser

import android.util.Log
import com.rateme.data.DataManager
import com.rateme.data.model.ResponseToken
import com.rateme.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginUserPresenter(private val dataManager: DataManager) :
    BasePresenter<LoginUserController.View>(),
    LoginUserController.Presenter {

    private lateinit var call: Call<ResponseToken>

    override fun responseTokenUser(phone: String, password: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getLoginUser(phone, password)
            call.enqueue(object : Callback<ResponseToken> {

                override fun onResponse(
                    call: Call<ResponseToken>,
                    loginResponse: Response<ResponseToken>
                ) {
                    it?.hideProgress()
                    if (loginResponse.isSuccessful) {
                        loginResponse.body()?.let { _ ->
                            it?.loginUser(loginResponse.body()!!)
                        }
                    }
                    Log.e("LoginUser", call.request().toString())
                }

                override fun onFailure(
                    call: Call<ResponseToken>,
                    t: Throwable
                ) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("ErrorUser", t.message.toString())
                }
            })
        }
    }
}