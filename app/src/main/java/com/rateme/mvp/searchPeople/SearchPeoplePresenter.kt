package com.rateme.mvp.searchPeople

import android.util.Log
import com.rateme.data.DataManager
import com.rateme.data.model.search.ResponseSearchPeople
import com.rateme.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("SENSELESS_COMPARISON")
class SearchPeoplePresenter(private val dataManager: DataManager) :
    BasePresenter<SearchPeopleController.View>(),
    SearchPeopleController.Presenter {

    private lateinit var call: Call<ResponseSearchPeople>

    override fun responseSearchPeople(search: String, session_id: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getSearchPeople(search, session_id)
            call.enqueue(object : Callback<ResponseSearchPeople> {

                override fun onResponse(
                    call: Call<ResponseSearchPeople>,
                    response: Response<ResponseSearchPeople>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getUsers() != null) {
                                it?.searchPeople(
                                    res.getUsers()!!,
                                    res.getFriends()!!
                                )
                            } else {
                                it?.notSearchUsers()
                            }
                        }
                    }
                    Log.e("ResponseSearch", call.request().toString())
                }

                override fun onFailure(call: Call<ResponseSearchPeople>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("ErrorResponseSearch", call.request().toString())
                }
            })
        }
    }
}