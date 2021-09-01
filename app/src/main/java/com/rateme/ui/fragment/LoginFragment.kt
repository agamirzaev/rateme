package com.rateme.ui.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.rateme.App
import com.rateme.R
import com.rateme.data.model.ResponseToken
import com.rateme.mvp.loginUser.LoginUserController
import com.rateme.mvp.loginUser.LoginUserPresenter
import com.rateme.ui.MainActivity
import com.rateme.ui.activity.MainActivityAccount
import com.rateme.ui.fragment.dialog.DialogErrorLoginFragment
import com.rateme.util.SPreferences

class LoginFragment : Fragment(), LoginUserController.View {
    private lateinit var createAccount: TextView
    private val bundle = Bundle()
    private lateinit var loginUserPresenter: LoginUserPresenter
    private lateinit var usernameMain: TextInputEditText
    private lateinit var passwordMain: TextInputEditText
    private lateinit var btnLogin: AppCompatButton
    private lateinit var constraintLoginView: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginUserPresenter =
            LoginUserPresenter((view.context.applicationContext as App).dataManager!!)
        loginUserPresenter.attachView(this@LoginFragment)

        constraintLoginView = view.findViewById(R.id.constraintLoginView)
        usernameMain = view.findViewById(R.id.usernameMain)
        passwordMain = view.findViewById(R.id.passwordMain)
        btnLogin = view.findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            loginUserPresenter.responseTokenUser(
                usernameMain.text.toString(),
                passwordMain.text.toString()
            )
        }
        createAccount = view.findViewById(R.id.createAccount)

        createAccount.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.fragment_register, bundle)
        }
        return view
    }


    override fun loginUser(responseToken: ResponseToken) {
        if (responseToken.getStatus() == true) {
            SPreferences.saveToken(responseToken.getToken(), requireContext())
            SPreferences.saveIdUser(responseToken.getIdUser(), requireContext())
            val intent = Intent(requireContext(), MainActivityAccount::class.java)
            startActivity(intent)
            requireActivity().finish()
            usernameMain.setText("")
            passwordMain.setText("")
        } else {
            (requireActivity() as MainActivity).createDialogFragment(
                DialogErrorLoginFragment(
                    "Неудачно",
                    responseToken.getMessage().toString()
                )
            )
        }
    }

    override fun onStart() {
        super.onStart()
        if (SPreferences.loadToken(requireContext()).toString() != "") {
            val intent = Intent(requireContext(), MainActivityAccount::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }
}