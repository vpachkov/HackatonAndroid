package com.example.slava.hackatonandroid.domain.utils

import android.content.Context
import android.util.TypedValue

object SizeConverter {
    fun convertDpToPixels(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()).toInt()
    }

    private fun deg2rad (deg: Double): Double{
        return deg * (Math.PI/180)
    }

    fun pointToPointDist(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        var radius = 6371
        var dLat = deg2rad(lat2-lat1)
        var dLon = deg2rad(lon2-lon1)
        var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2)
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))
        var d = radius * c
        return d
    }
}