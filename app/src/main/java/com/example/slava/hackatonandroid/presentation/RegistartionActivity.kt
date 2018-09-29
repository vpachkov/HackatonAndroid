package com.example.slava.hackatonandroid.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import com.example.slava.hackatonandroid.domain.utils.SizeConverter
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import kotlinx.android.synthetic.main.activity_registration.*

class RegisterActivity: AppCompatActivity() {

    private var gender = "defaultValue"
    private var countOfInterests = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        registration_container.addView(
                TextAdder.makeHatBlock("Welcome to" , "AppName" , this) , 0
        )
        registration_save.setOnClickListener {
            Log.e("ekkek" , "lol")
            if (checkRegistartionData()){
                Log.e("ekkek" , "lol")
            }
        }

        registration_male.setOnClickListener {
            drawGenderButtons()
            registration_male.background = getDrawable(R.drawable.clicked_gender_button)
            gender = "male"
        }
        registration_female.setOnClickListener {
            drawGenderButtons()
            registration_female.background = getDrawable(R.drawable.clicked_gender_button)
            gender = "female"
        }
        registration_other.setOnClickListener {
            drawGenderButtons()
            registration_other.background = getDrawable(R.drawable.clicked_gender_button)
            gender = "other"
        }

    }

    fun checkRegistartionData(): Boolean{
        var isAllCorrect = true
        for (item in 0 until registration_container.childCount){

            if (registration_container.getChildAt(item) is EditText){
                (registration_container.getChildAt(item) as EditText).setHintTextColor(resources.getColor(R.color.colorRed))
                isAllCorrect = false
            }
            if (registration_container.getChildAt(item) is LinearLayout){
                for (item2 in 0 until (registration_container.getChildAt(item) as LinearLayout).childCount){
                    if ((registration_container.getChildAt(item) as LinearLayout).getChildAt(item2) is EditText){
                        ((registration_container.getChildAt(item) as LinearLayout).getChildAt(item2) as EditText).setHintTextColor(resources.getColor(R.color.colorRed))
                        isAllCorrect = false
                    }
                }
            }
        }
        if (gender == "defaultValue"){
            registration_male.setTextColor(resources.getColor(R.color.colorRed))
            registration_female.setTextColor(resources.getColor(R.color.colorRed))
            registration_other.setTextColor(resources.getColor(R.color.colorRed))
            isAllCorrect = false
        }
        return isAllCorrect
    }


    private fun drawGenderButtons(){
        registration_male.background = getDrawable(R.drawable.gender_button)
        registration_female.background = getDrawable(R.drawable.gender_button)
        registration_other.background = getDrawable(R.drawable.gender_button)

        registration_male.setTextColor(resources.getColor(R.color.colorGrayBorder))
        registration_female.setTextColor(resources.getColor(R.color.colorGrayBorder))
        registration_other.setTextColor(resources.getColor(R.color.colorGrayBorder))
    }
}