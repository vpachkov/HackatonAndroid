package com.example.slava.hackatonandroid.domain

import android.app.IntentService
import android.app.Service
import android.content.Intent
import com.example.slava.hackatonandroid.domain.model.Position
import org.ankit.gpslibrary.MyTracker
import org.greenrobot.eventbus.EventBus

class LocationService : IntentService("NotificationsService") {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return Service.START_STICKY
    }

    override fun onHandleIntent(intent: Intent?) {
        while (true) {
            try {
                val tracker = MyTracker(this)
                EventBus.getDefault().post(Position(tracker.latitude, tracker.longitude))
                Thread.sleep(5000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}