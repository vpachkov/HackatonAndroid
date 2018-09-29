package com.example.slava.hackatonandroid.domain.utils

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.slava.hackatonandroid.R

object TextAdder {

    fun makeHatBlock(topLineText: String , bottomLineText: String , context: Context): LinearLayout{

        val hatBlock = LinearLayout(context)
        val topLine = TextView(context)
        val bottomline = TextView(context)
        val gradientView = View(context)

        val hatBlockParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT)
        val topLineParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT)
        val bottomLineParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT)
        val gradientViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , SizeConverter.convertDpToPixels(7f , context))

        topLineParams.setMargins(
                SizeConverter.convertDpToPixels(10f , context) , SizeConverter.convertDpToPixels(20f , context) , 0 , 0)

        bottomLineParams.setMargins(
                SizeConverter.convertDpToPixels(10f , context) , SizeConverter.convertDpToPixels(20f , context) , 0 , 0)

        gradientViewParams.setMargins( 0 , SizeConverter.convertDpToPixels(20f , context) , 0 , 0)


        topLine.layoutParams = topLineParams
        bottomline.layoutParams = bottomLineParams
        gradientView.layoutParams = gradientViewParams

        topLine.text = topLineText
        bottomline.text = bottomLineText

        topLine.setTextSize(TypedValue.COMPLEX_UNIT_SP , 30f)
        bottomline.setTextSize(TypedValue.COMPLEX_UNIT_SP , 35f)

        topLine.setTextColor(context.resources.getColor(R.color.colorGray))
        bottomline.setTextColor(context.resources.getColor(R.color.colorGray))

        bottomline.setTypeface(null , Typeface.BOLD)

        gradientView.setBackgroundDrawable(context.getDrawable(R.drawable.horizontal_gradient))

        hatBlock.orientation = LinearLayout.VERTICAL

        hatBlock.addView(topLine)
        hatBlock.addView(bottomline)
        hatBlock.addView(gradientView)

        return hatBlock
    }
}