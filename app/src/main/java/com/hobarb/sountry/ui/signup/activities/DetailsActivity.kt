package com.hobarb.sountry.ui.signup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        findViewById<AppCompatButton>(R.id.btn_next_ac_details).setOnClickListener{
           // intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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
        if(binding.etUsernameAcDetails.text.isNullOrBlank())
            binding.etUsernameAcDetails.error = "Enter a valid username"
        if(binding.etPasswordAcDetails.text.isNullOrBlank())
            binding.etPasswordAcDetails.error = "Enter a valid password"
        if(binding.etPhoneAcDetails.text.isNullOrBlank())
            binding.etPhoneAcDetails.error = "Enter a valid phone number"
        if(binding.etEmailAcDetails.text.isNullOrBlank())
            binding.etEmailAcDetails.error = "Enter a valid email"

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