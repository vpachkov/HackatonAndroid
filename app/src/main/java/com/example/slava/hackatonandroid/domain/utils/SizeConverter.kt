package com.example.slava.hackatonandroid.domain.utils

import android.content.Context
import android.util.TypedValue

object SizeConverter {
    fun convertDpToPixels(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()).toInt()
    }
}