package com.hobarb.sountry.ui.signup.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.hobarb.sountry.R
import com.hobarb.sountry.databinding.ActivityDetailsBinding
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.constants.UserDetails

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val maleBtn = binding.incMaleAcDetails.llMaleLayFSignup
        val femaleBtn = binding.incFemaleAcDetails.llFemaleLayFSignup


        maleBtn.setOnClickListener {
            femaleBtn.setBackgroundResource(R.drawable.bg_genre_signup)
            maleBtn.setBackgroundResource(R.drawable.bg_genre_signup_selected)
            binding.incMaleAcDetails.tvMaleSignUp.setTextColor(Color.WHITE)
            binding.incFemaleAcDetails.tvFemaleSignUp.setTextColor(Color.BLACK)

            constants.UserDetails.GENDER = "Male"
        }
        femaleBtn.setOnClickListener {
            maleBtn.setBackgroundResource(R.drawable.bg_genre_signup)
            femaleBtn.setBackgroundResource(R.drawable.bg_genre_signup_selected)
            binding.incFemaleAcDetails.tvFemaleSignUp.setTextColor(Color.WHITE)
            binding.incMaleAcDetails.tvMaleSignUp.setTextColor(Color.BLACK)

            constants.UserDetails.GENDER = "Female"
        }

        findViewById<AppCompatButton>(R.id.btn_next_ac_details).setOnClickListener{


            if(validateInputs())
            {
                startActivity(Intent(applicationContext, PreferencesActivity::class.java))
                this.overridePendingTransition(0, 0);
            }


        }








    }

    private fun validateInputs() : Boolean {
        if(binding.etNameAcDetails.text.isNullOrBlank())
           binding.etNameAcDetails.error = "Enter a valid name"
        else if(binding.etUsernameAcDetails.text.isNullOrBlank())
            binding.etUsernameAcDetails.error = "Enter a valid username"
        else if(binding.etPasswordAcDetails.text.isNullOrBlank())
            binding.etPasswordAcDetails.error = "Enter a valid password"
        else if(binding.etPhoneAcDetails.text.isNullOrBlank())
            binding.etPhoneAcDetails.error = "Enter a valid phone number"
        else if(binding.etEmailAcDetails.text.isNullOrBlank())
            binding.etEmailAcDetails.error = "Enter a valid email"
        else if(constants.UserDetails.GENDER == "")
        {
            Toast.makeText(applicationContext, "Select your gender", Toast.LENGTH_SHORT).show()
        }

        else{
            UserDetails.FULL_NAME = binding.etNameAcDetails.text.toString()
            UserDetails.USER_NAME = binding.etUsernameAcDetails.text.toString()
            UserDetails.PASSWORD = binding.etPasswordAcDetails.text.toString()
            UserDetails.PHONE = binding.etPhoneAcDetails.text.toString()
            UserDetails.EMAIL= binding.etEmailAcDetails.text.toString()

            return true;
        }

        return false;

    }
}