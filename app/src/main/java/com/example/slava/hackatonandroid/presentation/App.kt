package com.example.slava.hackatonandroid.presentation

import android.app.Application
import android.content.Intent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //startService(Intent(this, LocationService::class.java))
    }
}