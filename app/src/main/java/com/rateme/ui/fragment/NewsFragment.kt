package com.rateme.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.rateme.R


class NewsFragment : Fragment() {
    lateinit var tabLayout: TabLayout
    private lateinit var toolbarMainNews: androidx.appcompat.widget.Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        toolbarMainNews = view.findViewById(R.id.toolbar_main_news)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbarMainNews)
        tabLayout = view.findViewById(R.id.tabLayoutNews)
        
        tabLayoutFragment(NewsPostListFragment())

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        tabLayoutFragment(NewsPostListFragment())
                    }
                    1 -> {
                        tabLayoutFragment(ActionSubscriptionsFragment())
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

}