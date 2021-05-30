package com.hobarb.sountry.ui.user.activities

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.hobarb.sountry.R
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.databinding.ActivityVideoDetailBinding

import com.hobarb.sountry.models.ProfileModel
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.views.InflaterFunctions
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoDetailBinding
    lateinit var loader:Loader
    var user_id:Long = 0
    var id:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loader = Loader(this@VideoDetailActivity)
        loader.showAlertDialog()

        user_id = SharedPrefs(this).readPrefs(constants.USER_ID_KEY).toLong()
        val video_creator_id: String? = intent.getStringExtra("video_creator_id")
         id = video_creator_id!!.toLong()
        val video_url: String? = intent.getStringExtra("video_url")
        val video_date: String? = intent.getStringExtra("video_date")

        val video_id: String? = intent.getStringExtra("video_id")


        val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)





        binding.btnSendRequestAcVidDet.setOnClickListener {
            binding.btnSendRequestAcVidDet.setBackgroundResource(R.drawable.button_disabled)
            binding.btnSendRequestAcVidDet.setText("Request Sent")


            val call_1: Call<JsonObject>? = service.postSendRequest(id, user_id)
            call_1!!.enqueue(object : Callback<JsonObject>{
                override fun onResponse(call_1: Call<JsonObject>, response: Response<JsonObject>) {
                    Toaster.showToast(applicationContext, ""+response.body())
                }

                override fun onFailure(call_1: Call<JsonObject>, t: Throwable) {
                    Toaster.showToast(applicationContext, ""+t.message)
                }
            })


            }
        binding.btnChatAcVidDet.setOnClickListener {
            val intent = Intent(applicationContext, ChatActivity::class.java)
            intent.putExtra("user_id_1", user_id.toString())
            intent.putExtra("user_id_2", id.toString())
            //Toaster.showToast(applicationContext, user_id.toString())
            startActivity(intent)
            }

        if (user_id == id)
        {
            binding.btnSendRequestAcVidDet.visibility = View.GONE
        }
        else{
            checkConnection(service)
        }

        val call_1: Call<List<ProfileModel>>? = service.getUserProfile(id)
        call_1!!.enqueue(object : Callback<List<ProfileModel>>{

            override fun onResponse(call_1: Call<List<ProfileModel>>, response: Response<List<ProfileModel>>) {
                updateViews(response.body()!![0])
                val call_2: Call<List<String>>? = service.getVideoGenres(video_id)
                call_2!!.enqueue(object : Callback<List<String>>{
                    override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {

                        val listOfGenres: MutableList<String>? = response.body()!!.toMutableList()
                        if (listOfGenres != null) {
                            InflaterFunctions.inflateGenres(this@VideoDetailActivity, binding.llVideoGenresParentAcVidDet, listOfGenres)
                        }

                      
                        loader.dismissAlertDialog()


                    }

                    override fun onFailure(call: Call<List<String>>, t: Throwable) {
                        Toaster.showToast(applicationContext, ""+t.message)
                        loader.dismissAlertDialog()
                    }
                })

            }

            override fun onFailure(call_1: Call<List<ProfileModel>>, t: Throwable) {
                Toaster.showToast(applicationContext, ""+ t.message)
                loader.dismissAlertDialog()
            }
        })




            binding.wvVideoAcVidDet.loadUrl(video_url.toString())
        binding.tvDateCreatedAcVidDet.setText(""+video_date)
    }

    private fun checkConnection(service: ApiServices) {
        val loader  = Loader(this@VideoDetailActivity)
        loader.showAlertDialog()
        val call: Call<JsonObject>? = service.getCheckConnection(user_id, id)

        call!!.enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                if (response.body()!!["data"].toString() == "1")
                {
                    binding.btnSendRequestAcVidDet.visibility = View.GONE
                    binding.btnRequestedAcVidDet.visibility = View.GONE

                    binding.btnChatAcVidDet.visibility = View.VISIBLE
                }
                else if (response.body()!!["data"].toString() == "0")
                {
                    binding.btnSendRequestAcVidDet.visibility = View.GONE
                    binding.btnRequestedAcVidDet.visibility = View.VISIBLE
                }


                loader.dismissAlertDialog()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toaster.showToast(applicationContext, t.message)
                loader.dismissAlertDialog()
            }

        })

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