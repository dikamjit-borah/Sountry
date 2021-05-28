package com.hobarb.sountry.ui.user.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.databinding.ActivityVideoDetailBinding

import com.hobarb.sountry.models.ProfileModel
import com.hobarb.sountry.utilities.views.InflaterFunctions
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoDetailBinding
    lateinit var loader:Loader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loader = Loader(this@VideoDetailActivity)
        loader.showAlertDialog()
        val video_creator_id: String? = intent.getStringExtra("video_creator_id")
        val id:Long = video_creator_id!!.toLong()
        val video_url: String? = intent.getStringExtra("video_url")
        val video_date: String? = intent.getStringExtra("video_date")

        Toaster.showToast(applicationContext, ""+id)


        val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val call: Call<List<ProfileModel>>? = service.getUserProfile(id)
        call!!.enqueue(object : Callback<List<ProfileModel>>{

            override fun onResponse(call: Call<List<ProfileModel>>, response: Response<List<ProfileModel>>) {
                updateViews(response.body()!![0])
                loader.dismissAlertDialog()
            }

            override fun onFailure(call: Call<List<ProfileModel>>, t: Throwable) {
                Toaster.showToast(applicationContext, ""+ t.message)
                loader.dismissAlertDialog()
            }
        })

        binding.wvVideoAcVidDet.loadUrl(video_url.toString())
        binding.tvDateCreatedAcVidDet.setText(""+video_date)
    }


    private fun updateViews(profileModel: ProfileModel) {
        binding.tVUsernameAcVidDet.text = profileModel.up_user_name
        binding.tvCreatorIdAcVidDet.text = profileModel.up_user_id
        binding.tvFullnameAcVidDet.text = profileModel.up_full_name
        binding.tvPhoneAcVidDet.text = profileModel.up_phone
        binding.tvEmailAcVidDet.text = profileModel.up_email
        binding.tvGenderAcVidDet.text = profileModel.up_gender

        val listOfStrings = Gson().fromJson( profileModel.up_preferred_genres, mutableListOf<String>().javaClass)
        InflaterFunctions.inflateGenres(this@VideoDetailActivity, binding.llGenresParentAcVidDet, listOfStrings)

    }
}