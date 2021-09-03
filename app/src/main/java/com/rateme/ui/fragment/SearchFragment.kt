package com.rateme.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.rateme.App
import com.rateme.R
import com.rateme.adapter.AdapterSearchPeople
import com.rateme.data.model.search.Friends
import com.rateme.data.model.search.Users
import com.rateme.mvp.searchPeople.SearchPeopleController
import com.rateme.mvp.searchPeople.SearchPeoplePresenter
import com.rateme.util.SPreferences

@Suppress("SENSELESS_COMPARISON")
class SearchFragment : Fragment(), SearchPeopleController.View {
    private lateinit var resultSearch: TextView
    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var editTextSearch: TextInputEditText
    private lateinit var searchPeoplePresenter: SearchPeoplePresenter
    private lateinit var progressBarSearch: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        resultSearch = view.findViewById(R.id.resultSearch)
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch)
        editTextSearch = view.findViewById(R.id.editTextSearch)
        progressBarSearch = view.findViewById(R.id.progressBarSearch)
        searchPeoplePresenter =
            SearchPeoplePresenter((requireActivity().applicationContext as App).dataManager!!)
        searchPeoplePresenter.attachView(this@SearchFragment)

//        searchPeoplePresenter.responseSearchPeople(
//            editTextSearch.text.toString(),
//            SPreferences.loadIdUser(requireContext()).toString()
//        )

        resultSearch.visibility = View.GONE

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                search: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                if (editTextSearch.text.toString().trim() == "") {
                    progressBarSearch.visibility = View.GONE
                    resultSearch.visibility = View.GONE
                    recyclerViewSearch.visibility = View.GONE
                } else {
                    searchUsers(search.toString())
                    resultSearch.visibility = View.VISIBLE
                    recyclerViewSearch.visibility = View.VISIBLE
                }
            }

            override fun onTextChanged(search: CharSequence, start: Int, before: Int, count: Int) {
                if (editTextSearch.text.toString().trim() == "") {
                    progressBarSearch.visibility = View.GONE
                    resultSearch.visibility = View.GONE
                    recyclerViewSearch.visibility = View.GONE
                } else {
                    searchUsers(search.toString())
                    resultSearch.visibility = View.VISIBLE
                    recyclerViewSearch.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(search: Editable) {
                if (editTextSearch.text.toString().trim() == "") {
                    progressBarSearch.visibility = View.GONE
                    resultSearch.visibility = View.GONE
                    recyclerViewSearch.visibility = View.GONE
                } else {
                    searchUsers(search.toString())
                    resultSearch.visibility = View.VISIBLE
                    recyclerViewSearch.visibility = View.VISIBLE
                }
            }
        })

        return view
    }


    @SuppressLint("SetTextI18n")
    private fun searchUsers(search: String): String {
        if (search != "") {
            resultSearch.visibility = View.VISIBLE
            recyclerViewSearch.visibility = View.VISIBLE
            searchPeoplePresenter.responseSearchPeople(
                search,
                SPreferences.loadIdUser(requireContext()).toString()
            )
            resultSearch.text = "Результат поиска: @$search"
        } else {
            progressBarSearch.visibility = View.GONE
            recyclerViewSearch.visibility = View.GONE
            resultSearch.visibility = View.GONE
        }
        return search
    }

    override fun searchPeople(searchPeople: ArrayList<Users>?, friends: ArrayList<Friends>?) {
        val myAdapter = AdapterSearchPeople(searchPeople, friends)
        if (myAdapter != null) {
            recyclerViewSearch.adapter = myAdapter
        }
    }

    override fun showProgress() {
        progressBarSearch.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBarSearch.visibility = View.GONE
    }

    override fun noConnection() {
        progressBarSearch.visibility = View.GONE
    }

    override fun notSearchUsers() {
        resultSearch.text = "Результаты поиска: Нет такого пользователя"
    }
}