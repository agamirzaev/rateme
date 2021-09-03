package com.rateme.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.rateme.R

class UpdateUsernameActivity : AppCompatActivity() {

    private lateinit var usernameEditUpdate: TextInputEditText
    private lateinit var btnUpdateUsername: AppCompatButton
    private lateinit var resultUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_username)

        usernameEditUpdate = findViewById(R.id.usernameEditUpdate)
        btnUpdateUsername = findViewById(R.id.btnUpdateUsername)
        resultUsername = findViewById(R.id.resultUsername)
    }
}