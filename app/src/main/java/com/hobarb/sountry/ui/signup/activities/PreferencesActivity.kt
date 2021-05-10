package com.hobarb.sountry.ui.signup.activities

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import androidx.core.content.res.ResourcesCompat
import com.google.android.material.chip.Chip
import com.hobarb.sountry.R
import com.hobarb.sountry.ui.signup.adapters.GridAdapter
import com.hobarb.sountry.utilities.constants
import org.w3c.dom.Text
import java.util.*

class PreferencesActivity : AppCompatActivity() {
    lateinit var genres_gv: GridView
    lateinit var male_inc:View
    lateinit var female_inc:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        genres_gv = findViewById<GridView>(R.id.gv_genres_ac_prefs)
        male_inc = findViewById(R.id.inc_male_ac_prefs)
        female_inc = findViewById(R.id.inc_female_ac_prefs)

        val gridAdapter = GridAdapter(this, constants.genres)
        genres_gv.adapter = gridAdapter

        male_inc.setOnClickListener{
            male_inc.setBackgroundColor(Color.parseColor("#90caf9"))
        }

        female_inc.setOnClickListener{
            female_inc.setBackgroundColor(Color.parseColor("#ffe082"))
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