package com.hobarb.sountry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.hobarb.sountry.ui.login.LoginActivity
import com.hobarb.sountry.ui.signup.activities.DetailsActivity
import com.hobarb.sountry.ui.user.activities.DashboardActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnlogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        findViewById<Button>(R.id.btnsignup).setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
            finish()
        }
    }
}