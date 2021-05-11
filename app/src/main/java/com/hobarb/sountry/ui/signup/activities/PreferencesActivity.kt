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

        val gridAdapter = GridAdapter(this, constants.genres)
        genres_gv.adapter = gridAdapter

        male_inc.setOnClickListener{
            if (!is_male_selected)
            {
                constants.USER_PREFERRED_GENDERS.add(getString(R.string.male))
                male_inc.setBackgroundColor(Color.parseColor("#90caf9"))
                is_male_selected = true;
            }
            else
            {
                constants.USER_PREFERRED_GENDERS.remove(getString(R.string.male))
                male_inc.setBackgroundResource(R.drawable.bg_gender_signup)
                is_male_selected = false;
            }

        }
        female_inc.setOnClickListener{
            if (!is_female_selected)
            {

                constants.USER_PREFERRED_GENDERS.add(getString(R.string.female))
                female_inc.setBackgroundColor(Color.parseColor("#ffe082"))
                is_female_selected = true;
            }
            else
            {
                constants.USER_PREFERRED_GENDERS.remove(getString(R.string.female))
                female_inc.setBackgroundResource(R.drawable.bg_gender_signup)
                is_female_selected = false;
            }

        }

        findViewById<AppCompatButton>(R.id.btn_submit_ac_prefs).setOnClickListener{
            val jsonObject = JSONObject()
            jsonObject.put(constants.GENRES_KEY, constants.USER_PREFERRED_GENRES)
            jsonObject.put(constants.GENDERS_KEY, constants.USER_PREFERRED_GENDERS)
            Toast.makeText(applicationContext, ""+ jsonObject.toString() , Toast.LENGTH_SHORT).show()
        }

    }

    private fun inflateGenres(layout: GridView) {


        for (i in constants.genres.indices) {


            val genre: View =
                LayoutInflater.from(applicationContext).inflate(R.layout.layout_genre_signup, null)
            genre.findViewById<TextView>(R.id.tv_lay_genreSignUp).text = constants.genres.get(i)
            layout.addView(genre)
        }

    }
}