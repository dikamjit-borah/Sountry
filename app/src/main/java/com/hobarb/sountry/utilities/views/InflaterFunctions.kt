package com.hobarb.sountry.utilities.views

import android.R.attr.button
import android.R.attr.gravity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.hobarb.sountry.R


object InflaterFunctions {
    public  fun inflateGenres(
        context: Context,
        parent: LinearLayout,
        genres: MutableList<String>,
    ) {

        for (i in genres) {
            val genre: View =
                LayoutInflater.from(context).inflate(R.layout.layout_genre, null)

            val genreLayoutParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            genreLayoutParams.setMargins(0, 0, 10, 0)
            genreLayoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT

            genre.setLayoutParams(genreLayoutParams)
            val tv  = genre.findViewById<TextView>(R.id.tv_lay_genre)
            tv.text = i
            parent.addView(genre)
        }

    }

    public  fun inflateGender(
        context: Context,
        parent: LinearLayout,
        genres: MutableList<String>,
    ) {

        for (i in genres) {
            val genre: View =
                LayoutInflater.from(context).inflate(R.layout.layout_gender, null)

            val genreLayoutParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            genreLayoutParams.setMargins(0, 0, 10, 0)
            genreLayoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT

            genre.setLayoutParams(genreLayoutParams)
            val tv  = genre.findViewById<TextView>(R.id.tv_gender_lay_gender)
            tv.text = i
            parent.addView(genre)
        }

    }
}