package com.rateme.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rateme.R
import com.rateme.data.model.search.Friends
import com.rateme.data.model.search.Users
import com.rateme.ui.activity.MainActivityAccount
import com.rateme.ui.activity.ProfileUserActivity
import com.rateme.util.SPreferences
import com.squareup.picasso.Picasso

class AdapterSearchPeople(
    private var users: ArrayList<Users>?,
    private var friends: ArrayList<Friends>?
) :
    RecyclerView.Adapter<AdapterSearchPeople.ViewHolder>() {

    private lateinit var viewHolder: ViewHolder

    companion object {
        var USER_ID: String = "USER_ID"
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterSearchPeople.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_search_people, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterSearchPeople.ViewHolder, position: Int) {
        val itemUsers: Users = users!![position]
        viewHolder.bind(holder, itemUsers)
    }

    override fun getItemCount(): Int {
        return users!!.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {

        }

        private var avatarSearchPeople: ImageView = view.findViewById(R.id.avatarSearchPeople)
        private var usernameSearchPeople: TextView = view.findViewById(R.id.usernameSearchPeople)
        private var countRateUser: TextView = view.findViewById(R.id.countRateUser)

        @SuppressLint("SetTextI18n")
        fun bind(holder: ViewHolder, users: Users) {
            holder.usernameSearchPeople.text = users.getUsername().toString()
            Picasso.get().load(users.getAvatar()).into(holder.avatarSearchPeople)
            countRateUser.text = users.getRate().toString()


            itemView.setOnClickListener {
                if (users.getId().toString() == SPreferences.loadIdUser(holder.itemView.context)) {
                    (itemView.context as MainActivityAccount).myProfileView()
                } else {
                    val intent =
                        Intent(viewHolder.itemView.context, ProfileUserActivity::class.java)
                    intent.putExtra(USER_ID, users.getId().toString())
                    it.context.startActivity(intent)
                }
            }
        }
    }
}