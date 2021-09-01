package com.rateme.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rateme.R

class RegisterFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var haveAccount: TextView
    private lateinit var closeRegister: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        haveAccount = view.findViewById(R.id.haveAccount)
        closeRegister = view.findViewById(R.id.closeRegister)

        haveAccount()
        closeRegister()

        return view
    }

    private fun haveAccount() {
        haveAccount.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun closeRegister() {
        closeRegister.setOnClickListener {
            navController.popBackStack()
        }
    }

}