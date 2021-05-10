package com.hobarb.sountry.ui.signup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.hobarb.sountry.R

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        findViewById<AppCompatButton>(R.id.btn_next_ac_details).setOnClickListener{
            startActivity(Intent(applicationContext, PreferencesActivity::class.java))
        }
    }
}