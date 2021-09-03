package com.rateme.mvp.bookmarks

import android.util.Log
import com.rateme.data.DataManager
import com.rateme.data.model.bookMarks.ResponseBookMarks
import com.rateme.data.model.follower.Subscriptions
import com.rateme.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookMarksPresenter(private val dataManager: DataManager) :
    BasePresenter<BookMarksController.View>(),
    BookMarksController.Presenter {

    private lateinit var callBookMarks: Call<ResponseBookMarks>

    override fun responseBookMarks(user_id: String, session_id: String) {
        mvpView.let { it ->
            callBookMarks = dataManager.getBookMarks(user_id, session_id)
            callBookMarks.enqueue(object : Callback<ResponseBookMarks> {

                override fun onResponse(
                    call: Call<ResponseBookMarks>,
                    res: Response<ResponseBookMarks>
                ) {
                    if (res.isSuccessful) {
                        res.body()?.let { _ ->
                            it?.getBookMarks(res.body()!!)
                        }
                    }
                    Log.e("LoadDataMyProfile", call.request().toString())
                }

                override fun onFailure(
                    call: Call<ResponseBookMarks>,
                    t: Throwable
                ) {
                    Log.e("ErrorLoadDataMyProfile", t.message.toString())
                }
            })
        }
    }
}