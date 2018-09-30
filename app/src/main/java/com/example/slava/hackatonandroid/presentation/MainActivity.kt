package com.example.slava.hackatonandroid.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, ProcessingPhotoActivity::class.java))

        /*//if user hasn't been authorized move him to authorization
        if (!(PreferencesHelper.getSharedPreferenceBoolean(applicationContext, PreferencesHelper.KEY_IS_LOGINED, false))) {
            startActivity(Intent(this, LoginActivity::class.java))
        }*/

        main_activity_main_block.addView(
                TextAdder.makeHatBlock("Discover new", "Places", this)
        )
    }
}
