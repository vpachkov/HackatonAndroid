package com.example.slava.hackatonandroid.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import com.wonderkiln.camerakit.*
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
        camera.addCameraKitListener(object : CameraKitEventListener {
            override fun onVideo(p0: CameraKitVideo?) {
            }

            override fun onEvent(p0: CameraKitEvent?) {
            }

            override fun onImage(p0: CameraKitImage?) {
                val picture = p0!!.jpeg
                val result: Bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.size)
                onImageCaptured(result)
            }

            override fun onError(p0: CameraKitError?) {
            }
        })

        takePhotoButton.setOnClickListener {
            camera.captureImage()
        }
    }

    override fun onPause() {
        camera.stop()
        super.onPause()
    }

    fun onImageCaptured(bitmap: Bitmap) {
        print("kek")
    }
}