package com.hobarb.sountry.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.hobarb.sountry.R
import com.hobarb.sountry.ui.user.activities.DashboardActivity

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val et_email = findViewById<EditText>(R.id.et_email_ac_login)
        val et_password = findViewById<EditText>(R.id.et_password_ac_login)
        val btn_login = findViewById<AppCompatButton>(R.id.btn_login_ac_login)

        btn_login.setOnClickListener {
            if (et_email.text.isNullOrEmpty() ||  !Patterns.EMAIL_ADDRESS.matcher(et_email.text).matches())
                et_email.error = "Please enter a valid email"
            if (et_password.text.isNullOrEmpty())
                et_password.error = "Please enter your password"
            else
            {
                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                finish()
            }
        }





    }
}