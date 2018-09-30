package com.example.slava.hackatonandroid.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.data.PreferencesHelper
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import khttp.get
import kotlinx.android.synthetic.main.activity_login.*
import khttp.post
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete

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

            Log.e("kekek" , "kekeks")

            val loginString = login_activity_login.text.toString()
            val passwordString = login_activity_password.text.toString()

            val params = mapOf(
                    "login" to loginString,
                    "password" to passwordString
            )
            Log.e("kekek" , "dw")
            val context = this
            doAsync {
                Log.e("kekek" , "dw")
                val responseLogin = post("https://dookyheroky.herokuapp.com/api/user/auth/" , data = params)
                onComplete {
                    val token = responseLogin.jsonObject.getString("token")
                    PreferencesHelper.setSharedPreferenceBoolean(context , PreferencesHelper.KEY_IS_LOGINED , true)
                    PreferencesHelper.setSharedPreferenceString(context , PreferencesHelper.KEY_TOKEN, token)
                    startActivity(Intent(context, MainActivity::class.java))
                }
            }
        }
    }
}