package com.rateme.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.rateme.App
import com.rateme.R
import com.rateme.adapter.AdapterSearchPeople
import com.rateme.data.model.bookMarks.ResponseBookMarks
import com.rateme.data.model.follower.Subscriptions
import com.rateme.data.model.user.ResponseUser
import com.rateme.data.model.viewProfile.ResponseViewProfile
import com.rateme.mvp.bookmarks.BookMarksController
import com.rateme.mvp.bookmarks.BookMarksPresenter
import com.rateme.mvp.bookmarks.ViewProfileController
import com.rateme.mvp.bookmarks.ViewProfilePresenter
import com.rateme.mvp.follower.SubscribersController
import com.rateme.mvp.follower.SubscribersPresenter
import com.rateme.mvp.loadDataProfileUser.LoadDataProfileUserController
import com.rateme.mvp.loadDataProfileUser.LoadDataProfileUserPresenter
import com.rateme.ui.menu.PostRateFragment
import com.rateme.ui.menu.PostThreeFragment
import com.rateme.ui.menu.SavePostFragment
import com.rateme.util.SPreferences
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ProfileUserActivity : AppCompatActivity(), LoadDataProfileUserController.View,
    SubscribersController.View, ViewProfileController.View, BookMarksController.View {

    private lateinit var presenter: LoadDataProfileUserPresenter
    private lateinit var subscribersPresenter: SubscribersPresenter
    private lateinit var bookMarksPresenter: BookMarksPresenter
    private lateinit var viewProfilePresenter: ViewProfilePresenter

    private lateinit var backFromProfile: ImageView
    private lateinit var saveProfile: ImageView
    private lateinit var sendMessage: ImageView
    private lateinit var profileUserAvatarView: CircleImageView
    private lateinit var usernameProfileUserView: TextView
    private lateinit var ratingProfileUserView: TextView
    private lateinit var firstLastNameProfileUserView: TextView
    private lateinit var statusProfileUserView: TextView
    private lateinit var textViewFollow: TextView
    private lateinit var constraintInfoProfileUser: ConstraintLayout
    private lateinit var countPostUser: TextView
    private lateinit var countSubscriptionsUser: TextView
    private lateinit var countSubscribersUser: TextView
    private lateinit var tabLayout: TabLayout
    private lateinit var noInternetProfile: TextView
    private lateinit var btnClickRepeat: AppCompatButton
    private lateinit var progressBarProfile: ProgressBar
    private lateinit var nestedScrollViewProfile: NestedScrollView
    private lateinit var constraintNotifFollow: ConstraintLayout
    private lateinit var textViewInfoUserJoin: TextView

    lateinit var userId: String
    var flagJoin: Boolean = true
    var flagBookMarks: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)

        userId = intent.getStringExtra(AdapterSearchPeople.USER_ID).toString()

        backFromProfile = findViewById(R.id.backFromProfile)
        saveProfile = findViewById(R.id.saveProfile)
        sendMessage = findViewById(R.id.sendMessage)
        profileUserAvatarView = findViewById(R.id.profileUserAvatarView)
        usernameProfileUserView = findViewById(R.id.usernameProfileUserView)
        ratingProfileUserView = findViewById(R.id.ratingProfileUserView)
        firstLastNameProfileUserView = findViewById(R.id.firstLastNameProfileUserView)
        statusProfileUserView = findViewById(R.id.statusProfileUserView)
        textViewFollow = findViewById(R.id.textViewFollow)
        constraintInfoProfileUser = findViewById(R.id.constraintInfoProfileUser)
        countPostUser = findViewById(R.id.countPostUser)
        countSubscriptionsUser = findViewById(R.id.countSubscriptionsUser)
        countSubscribersUser = findViewById(R.id.countSubscribersUser)
        tabLayout = findViewById(R.id.tabLayoutUser)
        nestedScrollViewProfile = findViewById(R.id.nestedScrollViewProfileUser)
        noInternetProfile = findViewById(R.id.noInternetProfileUser)
        btnClickRepeat = findViewById(R.id.btnClickRepeatUser)
        progressBarProfile = findViewById(R.id.progressBarProfileUser)
        constraintNotifFollow = findViewById(R.id.constraintNotifFollow)
        textViewInfoUserJoin = findViewById(R.id.textViewInfoUserJoin)

        presenter =
            LoadDataProfileUserPresenter((application as App).dataManager!!)
        presenter.attachView(this@ProfileUserActivity)

        subscribersPresenter =
            SubscribersPresenter((application as App).dataManager!!)
        subscribersPresenter.attachView(this@ProfileUserActivity)

        bookMarksPresenter = BookMarksPresenter((application as App).dataManager!!)
        bookMarksPresenter.attachView(this@ProfileUserActivity)

        viewProfilePresenter = ViewProfilePresenter((application as App).dataManager!!)
        viewProfilePresenter.attachView(this@ProfileUserActivity)
        viewProfilePresenter.responseViewProfile(SPreferences.loadIdUser(this).toString(), userId)

        backFromProfile.setOnClickListener {
            finish()
        }

        presenter.responseProfileUser(
            userId,
            SPreferences.loadIdUser(this).toString()
        )

        tabLayoutFragment(PostThreeFragment())

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        tabLayoutFragment(PostThreeFragment())
                    }
                    1 -> {
                        tabLayoutFragment(PostRateFragment())
                    }
                    2 -> {
                        tabLayoutFragment(SavePostFragment())
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })

    }

    private fun tabLayoutFragment(fm: Fragment) {
        val ft: FragmentTransaction =
            supportFragmentManager.beginTransaction()
        ft.replace(R.id.containerTabLayout, fm)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }

    @SuppressLint("SetTextI18n")
    override fun loadProfileUser(user: ResponseUser) {
        try {
            textViewInfoUserJoin.text =
                "${user.getUsername()} подписался на вас, примите запрос чтобы видеть его/ее публикации"

            Picasso.get().load(user.getAvatar()).into(profileUserAvatarView)
            usernameProfileUserView.text = user.getUsername().toString()

            if (user.getFirstLastName().toString() != "") {
                firstLastNameProfileUserView.text = user.getFirstLastName().toString()
            } else {
                firstLastNameProfileUserView.visibility = View.GONE
            }

            if (user.getStatus().toString() != "") {
                statusProfileUserView.text = user.getStatus().toString()
            } else {
                statusProfileUserView.visibility = View.GONE
            }

            ratingProfileUserView.text = user.getRate().toString()
            countPostUser.text = user.getPost().toString()
            countSubscriptionsUser.text = user.getFollowing().toString()
            countSubscribersUser.text = user.getFollowers().toString()

            if (user.getRequestUserJoin() == 1) {
                textViewFollow.text = "Отписаться"
                flagJoin = false
                constraintNotifFollow.visibility = View.VISIBLE
            } else {
                textViewFollow.text = "Подписаться"
                flagJoin = true
            }

            if (user.getRequestMeJoin()!! == 1) {
                textViewFollow.text = "Отписаться"
                flagJoin = false
                constraintNotifFollow.visibility = View.GONE
            } else {
                textViewFollow.text = "Подписаться"
                flagJoin = true
            }

            if (user.getBookMarks()!! == 1) {
                flagBookMarks = false
                saveProfile.setImageResource(R.drawable.ic_bookmark_true)
            } else {
                flagBookMarks = true
                saveProfile.setImageResource(R.drawable.ic_bookmark_profile)
            }

            saveProfile.setOnClickListener {
                flagBookMarks = if (flagBookMarks) {
                    saveProfile.setImageResource(R.drawable.ic_bookmark_true)
                    bookMarksPresenter.responseBookMarks(
                        SPreferences.loadIdUser(this).toString(),
                        userId
                    )
                    false
                } else {
                    saveProfile.setImageResource(R.drawable.ic_bookmark_profile)
                    bookMarksPresenter.responseBookMarks(
                        SPreferences.loadIdUser(this).toString(),
                        userId
                    )
                    true
                }
            }

            textViewFollow.setOnClickListener {
                if (flagJoin) {
                    subscribersPresenter.responseSubscriptions(
                        SPreferences.loadIdUser(this).toString(),
                        userId,
                        "1"
                    )
                    textViewFollow.text = "Отписаться"
                    flagJoin = false
                } else {
                    subscribersPresenter.responseSubscriptions(
                        SPreferences.loadIdUser(this).toString(),
                        userId,
                        "1"
                    )
                    textViewFollow.text = "Подписаться"
                    flagJoin = true
                }
            }

        } catch (e: Exception) {
        }
    }

    override fun getBookMarks(bookMarks: ResponseBookMarks) {
        if (bookMarks.getStatus().toString() == "Add") {
            Toast.makeText(this, "Пользователь добавлен в сохраненные", Toast.LENGTH_LONG).show()
        } else if (bookMarks.getStatus().toString() == "Del") {
            Toast.makeText(this, "Пользователь убран из сохраненных", Toast.LENGTH_LONG).show()
        }
    }

    override fun getSubscriptions(subscriptions: Subscriptions) {
        if (subscriptions.getStatus().toString() == "Add") {
            textViewFollow.text = subscriptions.getMessage().toString()
        } else if (subscriptions.getStatus().toString() == "Del") {
            textViewFollow.text = subscriptions.getMessage().toString()
        }
    }

    override fun showProgress() {
        nestedScrollViewProfile.visibility = View.GONE
        progressBarProfile.visibility = View.VISIBLE
        noInternetProfile.visibility = View.GONE
        btnClickRepeat.visibility = View.GONE
    }

    override fun hideProgress() {
        nestedScrollViewProfile.visibility = View.VISIBLE
        progressBarProfile.visibility = View.GONE
        noInternetProfile.visibility = View.GONE
        btnClickRepeat.visibility = View.GONE
    }

    override fun noConnection() {
        nestedScrollViewProfile.visibility = View.GONE
        progressBarProfile.visibility = View.GONE
        noInternetProfile.visibility = View.VISIBLE
        btnClickRepeat.visibility = View.VISIBLE
    }

    override fun getViewProfile(responseViewProfile: ResponseViewProfile) {}
}