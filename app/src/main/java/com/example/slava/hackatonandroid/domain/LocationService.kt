package com.example.slava.hackatonandroid.domain

import android.app.IntentService
import android.app.Service
import android.content.Intent
import org.greenrobot.eventbus.EventBus

class LocationService : IntentService("NotificationsService") {
    override fun onCreate() {
        super.onCreate()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return Service.START_STICKY
    }

    override fun onHandleIntent(intent: Intent?) {

    }
}