package com.rateme.ui.fragment.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rateme.R
import com.rateme.ui.activity.MainActivityAccount
import com.rateme.util.SPreferences

class BottomSheetSettingProfileFragment() :
    BottomSheetDialogFragment() {
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    private lateinit var logautAccount: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet_delete_profile, container, false)
        logautAccount = view.findViewById(R.id.logautAccount)
        logautAccount.setOnClickListener {
            SPreferences.clearToken(requireContext())
            SPreferences.clearUserId(requireContext())
            dismiss()
            try {
                (activity as MainActivityAccount).main()
            } catch (e: Exception) {

            }
        }
        return view
    }
}