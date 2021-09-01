package com.rateme.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rateme.R
import com.rateme.ui.MainActivity
import com.rateme.ui.fragment.MyProfileFragment
import com.rateme.ui.fragment.NewsFragment
import com.rateme.ui.fragment.SearchFragment

class MainActivityAccount : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    private val bundle = Bundle()
    lateinit var navigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_account)

        navigationView = findViewById(R.id.navigation_main)
        navigationView.setOnNavigationItemSelectedListener(this)
        loadFragment(NewsFragment())
    }

    private fun loadFragment(fm: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.content, fm)
        ft.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(NewsFragment())
                return true
            }
            R.id.navigation_search -> {
                loadFragment(SearchFragment())
                return true
            }
            R.id.navigation_notif_star -> {
//                loadFragment(PostFragment())
                return true
            }
            R.id.navigation_profile -> {
                loadFragment(MyProfileFragment())
                return true
            }
        }
        return false
    }

    override fun onBackPressed() {
        if (navigationView.selectedItemId == R.id.navigation_home) {
            super.onBackPressed()
            finish()
        } else {
            navigationView.selectedItemId = R.id.navigation_home
        }
    }

    fun myProfileView() {
        navigationView.selectedItemId = R.id.navigation_profile
    }

    fun main() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}