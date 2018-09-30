package com.example.slava.hackatonandroid.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import kotlinx.android.synthetic.main.activity_dicover_place.*

class PlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)

        parentLayout.addView(TextAdder.makeHatBlock("Discover",
                "The place",
                this))

        for (i in 1..10) {
            parentLayout.addView(TextAdder.makeItemPlaces(this,
                    "Lol",
                    "Kek"))
        }
    }
}