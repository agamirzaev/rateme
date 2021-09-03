package com.rateme.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.rateme.App
import com.rateme.R
import com.rateme.data.model.myProfile.UserResponse
import com.rateme.mvp.loadDataMyProfile.LoadDataMyProfileController
import com.rateme.mvp.loadDataMyProfile.LoadDataMyProfilePresenter
import com.rateme.mvp.loadDataProfileUser.LoadDataProfileUserController
import com.rateme.mvp.loadDataProfileUser.LoadDataProfileUserPresenter
import com.rateme.ui.activity.EditProfileActivity
import com.rateme.ui.fragment.bottom_sheet.BottomSheetCreatePostFragment
import com.rateme.ui.fragment.bottom_sheet.BottomSheetSettingProfileFragment
import com.rateme.ui.menu.PostRateFragment
import com.rateme.ui.menu.PostThreeFragment
import com.rateme.ui.menu.SavePostFragment
import com.rateme.util.SPreferences
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MyProfileFragment : Fragment(), LoadDataMyProfileController.View {
    private lateinit var tabLayout: TabLayout

    private lateinit var loadDataMyProfilePresenter: LoadDataMyProfilePresenter

    private lateinit var profileAvatarView: CircleImageView
    private lateinit var usernameProfileView: TextView
    private lateinit var ratingProfileView: TextView
    private lateinit var firstLastNameProfileView: TextView
    private lateinit var statusProfileView: TextView
    private lateinit var countPost: TextView
    private lateinit var countSubscriptions: TextView
    private lateinit var countSubscribers: TextView
    private lateinit var nestedScrollViewProfile: NestedScrollView
    private lateinit var noInternetProfile: TextView
    private lateinit var btnClickRepeat: AppCompatButton
    private lateinit var progressBarProfile: ProgressBar
    private lateinit var settingProfile: ImageView
    private lateinit var menuProfile: ImageView
    private lateinit var editProfile: TextView
    private lateinit var constraintCreate: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)
        loadDataMyProfilePresenter =
            LoadDataMyProfilePresenter((requireActivity().applicationContext as App).dataManager!!)
        loadDataMyProfilePresenter.attachView(this@MyProfileFragment)
        tabLayout = view.findViewById(R.id.tabLayout)
        profileAvatarView = view.findViewById(R.id.profileAvatarView)
        usernameProfileView = view.findViewById(R.id.usernameProfileView)
        ratingProfileView = view.findViewById(R.id.ratingProfileView)
        firstLastNameProfileView = view.findViewById(R.id.firstLastNameProfileView)
        statusProfileView = view.findViewById(R.id.statusProfileView)
        countPost = view.findViewById(R.id.countPost)
        countSubscriptions = view.findViewById(R.id.countSubscriptions)
        countSubscribers = view.findViewById(R.id.countSubscribers)
        settingProfile = view.findViewById(R.id.settingProfile)
        menuProfile = view.findViewById(R.id.menuProfile)
        constraintCreate = view.findViewById(R.id.constraintCreate)

        nestedScrollViewProfile = view.findViewById(R.id.nestedScrollViewProfile)
        noInternetProfile = view.findViewById(R.id.noInternetProfile)
        btnClickRepeat = view.findViewById(R.id.btnClickRepeat)
        progressBarProfile = view.findViewById(R.id.progressBarProfile)

        editProfile = view.findViewById(R.id.editProfile)

        btnClickRepeat.setOnClickListener {
            loadDataMyProfilePresenter.responseMyProfile(
                SPreferences.loadToken(requireContext()).toString(),
                SPreferences.loadIdUser(requireContext()).toString()
            )
        }

        loadDataMyProfilePresenter.responseMyProfile(
            SPreferences.loadToken(requireContext()).toString(),
            SPreferences.loadIdUser(requireContext()).toString()
        )

        constraintCreate.setOnClickListener {
            val bottomSheetFragment = BottomSheetCreatePostFragment()
            fragmentManager?.let { it1 -> bottomSheetFragment.show(it1, "TAG") }
        }

        menuProfile.setOnClickListener {
            val bottomSheetFragment = BottomSheetSettingProfileFragment()
            fragmentManager?.let { it1 -> bottomSheetFragment.show(it1, "TAG") }
        }
        settingProfile.setOnClickListener {

        }

        editProfile.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            requireActivity().startActivity(intent)
        }

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

        return view
    }

    private fun tabLayoutFragment(fm: Fragment) {
        val ft: FragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
        ft.replace(R.id.containerTabLayout, fm)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }

    override fun loadMyProfile(user: UserResponse) {
        try {

            Picasso.get().load(user.getAvatar()).into(profileAvatarView)
            usernameProfileView.text = user.getUsername().toString()
            if (user.getFirstLastName().toString() != "") {
                firstLastNameProfileView.text = user.getFirstLastName().toString()
            } else {
                firstLastNameProfileView.visibility = View.GONE
            }

            if (user.getStatus().toString() != "") {
                statusProfileView.text = user.getStatus().toString()
            } else {
                statusProfileView.visibility = View.GONE
            }

            statusProfileView.setOnClickListener {

            }

            firstLastNameProfileView.text = user.getFirstLastName().toString()
            ratingProfileView.text = user.getRate().toString()
            statusProfileView.text = user.getStatus().toString()
            countPost.text = user.getPost().toString()
            countSubscriptions.text = user.getFollowing().toString()
            countSubscribers.text = user.getFollowers().toString()

        } catch (e: Exception) {
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


}

