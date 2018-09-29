package com.example.slava.hackatonandroid.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import kotlinx.android.synthetic.main.activity_processing_photo.*

class ProcessingPhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_processing_photo)

        parentLayout.addView(TextAdder.makeHatBlock("Processing", "The photo", this), 0)
    }

    override fun onResume() {
        super.onResume()
        camera.start()
    }

    override fun onPause() {
        camera.stop()
        super.onPause()
    }
}