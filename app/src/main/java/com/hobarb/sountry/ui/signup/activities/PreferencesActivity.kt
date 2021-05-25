package com.hobarb.sountry.ui.signup.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.gson.JsonObject
import com.hobarb.sountry.R
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.models.UserModel
import com.hobarb.sountry.ui.login.LoginActivity
import com.hobarb.sountry.ui.signup.adapters.GridAdapter
import com.hobarb.sountry.ui.user.activities.DashboardActivity
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.constants.*
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PreferencesActivity : AppCompatActivity() {
    lateinit var genres_gv: GridView
    lateinit var male_inc:View
    lateinit var female_inc:View
    var is_male_selected = false;
    var is_female_selected = false;
    lateinit var loader:Loader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        loader = Loader(this@PreferencesActivity)
        genres_gv = findViewById<GridView>(R.id.gv_genres_ac_prefs)
        male_inc = findViewById(R.id.inc_male_ac_prefs)
        female_inc = findViewById(R.id.inc_female_ac_prefs)

        val gridAdapter = GridAdapter(this, genres)
        genres_gv.adapter = gridAdapter

        male_inc.setOnClickListener{
            if (!is_male_selected)
            {
                USER_PREFERRED_GENDER.add(getString(R.string.male))
                male_inc.setBackgroundResource(R.drawable.bg_genre_signup_selected)
                male_inc.findViewById<TextView>(R.id.tv_maleSignUp).setTextColor(Color.WHITE)
                is_male_selected = true;
            }
            else
            {
                USER_PREFERRED_GENDER.remove(getString(R.string.male))
                male_inc.setBackgroundResource(R.drawable.bg_gender_signup)
                male_inc.findViewById<TextView>(R.id.tv_maleSignUp).setTextColor(Color.BLACK)
                is_male_selected = false;
            }

        }
        female_inc.setOnClickListener{
            if (!is_female_selected)
            {

                USER_PREFERRED_GENDER.add(getString(R.string.female))
                female_inc.setBackgroundResource(R.drawable.bg_genre_signup_selected)
                female_inc.findViewById<TextView>(R.id.tv_femaleSignUp).setTextColor(Color.WHITE)
                is_female_selected = true;
            }
            else
            {
                USER_PREFERRED_GENDER.remove(getString(R.string.female))
                female_inc.setBackgroundResource(R.drawable.bg_gender_signup)
                female_inc.findViewById<TextView>(R.id.tv_femaleSignUp).setTextColor(Color.BLACK)
                is_female_selected = false;
            }

        }

        findViewById<AppCompatButton>(R.id.btn_submit_ac_prefs).setOnClickListener{

            loader.showAlertDialog()
            getUserInfo()
        }

    }

    private fun createUser(userInfo: UserModel) {
        val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val call: Call<JsonObject>? = service.postNewUser(userInfo)
        call!!.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                loader.dismissAlertDialog()
                val res:JsonObject = response.body()!!
                if(res["status"].toString() == "200") {
                    Toaster.showToast(this@PreferencesActivity, res["data"].asJsonObject["message"].toString())
                    startActivity(Intent(this@PreferencesActivity, LoginActivity::class.java))
                    finish()
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
               Toaster.showToast(this@PreferencesActivity, ""+t.message)
            }

        })

    }

    private fun getUserInfo() {


        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        UserDetails.DATE_JOINED = dateFormat.format(date).toString()

        val userDetailsDTO: UserModel.UserDetailsDTO = UserModel.UserDetailsDTO(UserDetails.FULL_NAME, UserDetails.USER_NAME, UserDetails.PASSWORD, UserDetails.GENDER, UserDetails.PHONE, UserDetails.EMAIL, UserDetails.DATE_JOINED)
        val userPreferencesDTO:UserModel.UserPreferencesDTO = UserModel.UserPreferencesDTO(USER_PREFERRED_GENRES, USER_PREFERRED_GENDER)
        val userModel:UserModel = UserModel(userDetailsDTO, userPreferencesDTO)

        createUser(userModel)

    }

    private fun inflateGenres(layout: GridView) {


        for (i in genres.indices) {


            val genre: View =
                LayoutInflater.from(applicationContext).inflate(R.layout.layout_genre_signup, null)
            genre.findViewById<TextView>(R.id.tv_lay_genreSignUp).text = genres.get(i)
            layout.addView(genre)
        }

    }
}