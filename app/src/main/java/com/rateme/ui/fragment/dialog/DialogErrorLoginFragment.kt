package com.rateme.ui.fragment.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.rateme.R

class DialogErrorLoginFragment(private val title: String, private val content: String) :
    DialogFragment() {

    private lateinit var titleError: TextView
    private lateinit var contentError: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_error_login, container, false)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        titleError = view.findViewById(R.id.titleError)
        contentError = view.findViewById(R.id.contentError)

        titleError.text = title
        contentError.text = content
        return view
    }
}