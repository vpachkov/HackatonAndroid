package com.example.slava.hackatonandroid.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.slava.hackatonandroid.domain.utils.SizeConverter
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.data.PreferencesHelper
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import khttp.post
import kotlinx.android.synthetic.main.activity_registration.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete
import org.jetbrains.anko.toast

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
                val loginString = registration_login.text.toString()
                val passwordString = registration_password.text.toString()
                val nameString = registration_name.text.toString()
                val cityString = registration_name.text.toString()
                val ageString = registration_age.text.toString()
                val context = this
                val params = mapOf(
                        "login" to loginString,
                        "password" to passwordString,
                        "name" to nameString,
                        "sex" to gender,
                        "age" to ageString,
                        "info" to "fsfdsf"
                )

                doAsync {
                    val responseRegistartion = post("https://dookyheroky.herokuapp.com/api/user/addnew/" , data = params)
                    onComplete {
                        val token = responseRegistartion.jsonObject.getString("token")
                        PreferencesHelper.setSharedPreferenceBoolean(context , PreferencesHelper.KEY_IS_LOGINED , true)
                        PreferencesHelper.setSharedPreferenceString(context , PreferencesHelper.KEY_TOKEN, token)
                        startActivity(Intent(context, MainActivity::class.java))
                    }
                }


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

            if (registration_password.text.toString() != registration_repeat_password.text.toString()){
                toast("Passwords are not equal")
                isAllCorrect = false
            }
        }
        if (gender == "defaultValue"){
            registration_male.setTextColor(resources.getColor(R.color.colorRed))
            registration_female.setTextColor(resources.getColor(R.color.colorRed))
            registration_other.setTextColor(resources.getColor(R.color.colorRed))
            isAllCorrect = false
        }
        return true
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