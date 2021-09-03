package com.rateme.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.rateme.R

class EditProfileActivity : AppCompatActivity() {
    private lateinit var constraintClickBack: ConstraintLayout
    private lateinit var progressUpdateDate: ProgressBar
    private lateinit var usernameEdit: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        constraintClickBack = findViewById(R.id.constraintClickBack)
        progressUpdateDate = findViewById(R.id.progressUpdateDate)
        usernameEdit = findViewById(R.id.usernameEdit)

        usernameEdit.setOnClickListener {
            val intent = Intent(this, UpdateUsernameActivity::class.java)
           startActivity(intent)
        }

        constraintClickBack.setOnClickListener {
            finish()
        }
    }
}