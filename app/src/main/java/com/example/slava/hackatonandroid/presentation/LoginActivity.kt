package com.example.slava.hackatonandroid.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_activity_main_block.addView(
                TextAdder.makeHatBlock("Welcome to", "AppName", this), 0
        )

        register_button.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}