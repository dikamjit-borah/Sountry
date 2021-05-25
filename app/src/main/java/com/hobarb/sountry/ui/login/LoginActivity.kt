package com.hobarb.sountry.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.hobarb.sountry.R
import com.hobarb.sountry.databinding.ActivityLoginBinding
import com.hobarb.sountry.ui.user.activities.DashboardActivity
import com.hobarb.sountry.utilities.constants
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {


    private  lateinit var binding: ActivityLoginBinding
    lateinit var user_credentials: JSONObject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user_credentials = JSONObject()
        val btn_login = findViewById<AppCompatButton>(R.id.btn_login_ac_login)

        btn_login.setOnClickListener {

            if(validateInput())
            {
                Toast.makeText(applicationContext, "" + user_credentials.toString(), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                finish()
            }
        }





    }

    private fun validateInput(): Boolean {
        if (binding.etEmailAcLogin.text.isNullOrEmpty() ||  !Patterns.EMAIL_ADDRESS.matcher(binding.etEmailAcLogin.text).matches())
            binding.etEmailAcLogin.error = "Please enter a valid email"
        if (binding.etPasswordAcLogin.text.isNullOrEmpty())
            binding.etPasswordAcLogin.error = "Please enter your password"

        else
        {
            user_credentials.put(constants.UserDetails.USER_NAME_KEY, binding.etEmailAcLogin.text.toString() )
            user_credentials.put(constants.UserDetails.PASSWORD_KEY, binding.etPasswordAcLogin.text.toString() )
            return true;
        }

        return false;
    }
}