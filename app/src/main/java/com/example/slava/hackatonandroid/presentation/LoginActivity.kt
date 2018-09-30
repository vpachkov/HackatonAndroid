package com.example.slava.hackatonandroid.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import kotlinx.android.synthetic.main.activity_login.*
import khttp.post

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

        login_activity_login_button.setOnClickListener {
            val loginString = login_activity_login.text.toString()
            val passwordString = login_activity_password.text.toString()

            val params = mapOf(
                    "login" to loginString,
                    "password" to passwordString
            )

            val responseLogin = post("https://https://dookyheroky.herokuapp.com/admin/api/user/addnew/" , params = params)
            Log.e("kekek" , responseLogin.text.toString())
        }
    }
}