package com.example.slava.hackatonandroid.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_activity_main_block.addView(
                TextAdder.makeHatBlock("Discover new " , "Places", this)
        )
    }
}
