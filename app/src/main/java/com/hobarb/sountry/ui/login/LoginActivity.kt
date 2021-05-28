package com.hobarb.sountry.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.google.gson.JsonObject
import com.hobarb.sountry.R
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.databinding.ActivityLoginBinding
import com.hobarb.sountry.ui.signup.activities.DetailsActivity
import com.hobarb.sountry.ui.user.activities.DashboardActivity
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    private  lateinit var binding: ActivityLoginBinding

    lateinit var loader:Loader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val sharedPrefs = SharedPrefs(this@LoginActivity)
        val token  = sharedPrefs.readPrefs(constants.TOKEN_KEY).toString()
        if(!token.isNullOrEmpty() && !(token == "404"))
        {
            Toaster.showToast(applicationContext, "" + sharedPrefs.readPrefs(constants.TOKEN_KEY).toString())
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            finish()
        }

        loader = Loader(this@LoginActivity)

        val btn_login = findViewById<AppCompatButton>(R.id.btn_login_ac_login)

        btn_login.setOnClickListener {


            if(validateInput())
            {
                loader.showAlertDialog()
                authenticateUser()
            }
        }

        binding.tvSignupAcLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, DetailsActivity::class.java))
        }






    }

    private fun authenticateUser() {
        val services:ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val jsonObject:JsonObject  = JsonObject();
        jsonObject.addProperty(constants.UserDetails.USER_NAME_KEY,constants.UserDetails.USER_NAME)
        jsonObject.addProperty(constants.UserDetails.PASSWORD_KEY,constants.UserDetails.PASSWORD )

        val call = services.postSignIn(jsonObject)
        call.enqueue(object : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val res:JsonObject = response.body()!!

                loader.dismissAlertDialog()
                if(res["status"].toString() == "200"){
                    val sharedPrefs = SharedPrefs(this@LoginActivity)
                    val user_id:String = res["data"].asJsonObject["user_id"].toString().replace("\"", "")
                    sharedPrefs.writePrefs(constants.USER_ID_KEY, user_id)

                    val token:String = res["data"].asJsonObject["token"].toString().replace("\"", "")
                    sharedPrefs.writePrefs(constants.TOKEN_KEY, token)

                    Toaster.showToast(applicationContext,"Logged in as user " + user_id + " with token " + token)

                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                    finish()
                }
                else
                {
                    Toaster.showToast(applicationContext, ""+res)
                }


            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toaster.showToast(applicationContext, "Error: "+t.message)

            }

        })
    }

    private fun validateInput(): Boolean {
        // ||  !Patterns.EMAIL_ADDRESS.matcher(binding.etEmailAcLogin.text).matches()
        if (binding.etEmailAcLogin.text.isNullOrEmpty())
            binding.etEmailAcLogin.error = "Please enter email"
        if (binding.etPasswordAcLogin.text.isNullOrEmpty())
            binding.etPasswordAcLogin.error = "Please enter password"

        else
        {
            constants.UserDetails.USER_NAME = binding.etEmailAcLogin.text.toString()
            constants.UserDetails.PASSWORD = binding.etPasswordAcLogin.text.toString()
            return true;
        }

        return false;
    }
}