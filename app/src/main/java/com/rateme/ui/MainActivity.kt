package com.rateme.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rateme.R
import com.rateme.util.SPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    fun createDialogFragment(fragment: DialogFragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragment.show(fragmentManager, "DialogAction")
    }

    override fun onBackPressed() {
        if (SPreferences.loadToken(this).toString() != "") {
            super.onBackPressed()
            navController.popBackStack()
        } else {
            finish()
        }
    }
}