package com.hobarb.sountry.ui.signup.activities

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

         genres_gv = findViewById<GridView>(R.id.gv_genres_ac_prefs);

    val gridAdapter = GridAdapter(this, constants.genres);
        genres_gv.adapter = gridAdapter;
    //inflateGenres(genres_gv)

    }

    private fun inflateGenres(layout: GridView) {


        for (i in constants.genres.indices) {


            val genre:View = LayoutInflater.from(applicationContext).inflate(R.layout.layout_genre_signup, null)
            genre.findViewById<TextView>(R.id.tv_lay_genreSignUp).setText(constants.genres.get(i))
            layout.addView(genre)
        }

    }
}