package com.example.slava.hackatonandroid.presentation

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import kotlinx.android.synthetic.main.activity_processing_photo.*
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import android.R.attr.y
import android.R.attr.x
import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.Color
import android.R.array
import java.nio.ByteBuffer
import android.R.attr.bitmap
import com.example.slava.hackatonandroid.data.PreferencesHelper
import com.example.slava.hackatonandroid.domain.utils.ImageClassifier
import com.example.slava.hackatonandroid.presentation.fragments.Camera2BasicFragment
import java.nio.ByteOrder


class ProcessingPhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_processing_photo)
        parentLayout.addView(
                TextAdder.makeHatBlock("Processing the" , "photo" , this)  , 0)


        if (null == savedInstanceState) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit()
        }
    }

}