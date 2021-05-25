package com.hobarb.sountry.ui.signup.activities

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

import com.hobarb.sountry.R
import com.hobarb.sountry.ui.signup.adapters.GridAdapter
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.constants.*
import org.json.JSONObject

class PreferencesActivity : AppCompatActivity() {
    lateinit var genres_gv: GridView
    lateinit var male_inc:View
    lateinit var female_inc:View
    var is_male_selected = false;
    var is_female_selected = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        genres_gv = findViewById<GridView>(R.id.gv_genres_ac_prefs)
        male_inc = findViewById(R.id.inc_male_ac_prefs)
        female_inc = findViewById(R.id.inc_female_ac_prefs)

        val gridAdapter = GridAdapter(this, genres)
        genres_gv.adapter = gridAdapter

        male_inc.setOnClickListener{
            if (!is_male_selected)
            {
                USER_PREFERRED_GENDERS.add(getString(R.string.male))
                male_inc.setBackgroundResource(R.drawable.bg_genre_signup_selected)
                male_inc.findViewById<TextView>(R.id.tv_maleSignUp).setTextColor(Color.WHITE)
                is_male_selected = true;
            }
            else
            {
                USER_PREFERRED_GENDERS.remove(getString(R.string.male))
                male_inc.setBackgroundResource(R.drawable.bg_gender_signup)
                male_inc.findViewById<TextView>(R.id.tv_maleSignUp).setTextColor(Color.BLACK)
                is_male_selected = false;
            }

        }
        female_inc.setOnClickListener{
            if (!is_female_selected)
            {

                USER_PREFERRED_GENDERS.add(getString(R.string.female))
                female_inc.setBackgroundResource(R.drawable.bg_genre_signup_selected)
                female_inc.findViewById<TextView>(R.id.tv_femaleSignUp).setTextColor(Color.WHITE)
                is_female_selected = true;
            }
            else
            {
                USER_PREFERRED_GENDERS.remove(getString(R.string.female))
                female_inc.setBackgroundResource(R.drawable.bg_gender_signup)
                female_inc.findViewById<TextView>(R.id.tv_femaleSignUp).setTextColor(Color.BLACK)
                is_female_selected = false;
            }

        }

        findViewById<AppCompatButton>(R.id.btn_submit_ac_prefs).setOnClickListener{


            val user_info:JSONObject =  getUserInfo()
            Toast.makeText(applicationContext, ""+ user_info.toString() , Toast.LENGTH_SHORT).show()
        }

    }

    private fun getUserInfo(): JSONObject {
        val preferences = JSONObject()

        preferences.put(GENRES_KEY, USER_PREFERRED_GENRES)
        preferences.put(GENDERS_KEY, USER_PREFERRED_GENDERS)


        val details = JSONObject()

        details.put(UserDetails.FULL_NAME_KEY, UserDetails.FULL_NAME)
        details.put(UserDetails.USER_NAME_KEY, UserDetails.USER_NAME)
        details.put(UserDetails.PASSWORD_KEY, UserDetails.PASSWORD)
        details.put(UserDetails.PHONE_KEY, UserDetails.PHONE)
        details.put(UserDetails.EMAIL_KEY, UserDetails.EMAIL)


        val user_info = JSONObject()
        user_info.put(USER_PREFERENCES_KEY, preferences)
        user_info.put(USER_DETAILS_KEY, details)

        return user_info

    }

    private fun inflateGenres(layout: GridView) {


        for (i in genres.indices) {


            val genre: View =
                LayoutInflater.from(applicationContext).inflate(R.layout.layout_genre_signup, null)
            genre.findViewById<TextView>(R.id.tv_lay_genreSignUp).text = genres.get(i)
            layout.addView(genre)
        }

    }
}