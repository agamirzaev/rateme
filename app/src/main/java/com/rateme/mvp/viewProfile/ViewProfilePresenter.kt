package com.rateme.mvp.bookmarks

import android.util.Log
import com.rateme.data.DataManager
import com.rateme.data.model.bookMarks.ResponseBookMarks
import com.rateme.data.model.viewProfile.ResponseViewProfile
import com.rateme.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewProfilePresenter(private val dataManager: DataManager) :
    BasePresenter<ViewProfileController.View>(),
    ViewProfileController.Presenter {

    private lateinit var callViewProfile: Call<ResponseViewProfile>


    override fun responseViewProfile(user_id: String, session_id: String) {
        mvpView.let { it ->
            callViewProfile = dataManager.getViewProfile(user_id, session_id)
            callViewProfile.enqueue(object : Callback<ResponseViewProfile> {

                override fun onResponse(
                    call: Call<ResponseViewProfile>,
                    res: Response<ResponseViewProfile>
                ) {
                    if (res.isSuccessful) {
                        res.body()?.let { _ ->
                            it?.getViewProfile(res.body()!!)
                        }
                    }
                    Log.e("LoadDataMyProfile", call.request().toString())
                }

                override fun onFailure(
                    call: Call<ResponseViewProfile>,
                    t: Throwable
                ) {
                    Log.e("ErrorLoadDataMyProfile", t.message.toString())
                }
            })
        }
    }
}