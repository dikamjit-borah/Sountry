package com.hobarb.sountry.ui.user.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.hobarb.sountry.R
import com.hobarb.sountry.adapters.VideosAdapter
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.models.VideosModel
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedFragment : Fragment(R.layout.fragment_feed) {

    lateinit var videos_rv:RecyclerView

    lateinit var loader: Loader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videos_rv = view.findViewById(R.id.rv_videos_frag_feed)




        loader = Loader(context)

        fetchVideos()
    }

    private fun fetchVideos() {
        val my_user_id = SharedPrefs(context).readPrefs(constants.USER_ID_KEY)
        loader.showAlertDialog()
        val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val jsonObject:JsonObject = JsonObject()
        jsonObject.addProperty("user_id", my_user_id)
        val call: Call<List<VideosModel>>? = service.getVideos(jsonObject)

        call!!.enqueue(object : Callback<List<VideosModel>>{
            override fun onResponse(
                call: Call<List<VideosModel>>,
                response: Response<List<VideosModel>>
            ) {


               // Toaster.showToast(context, response.body().toString())
                if(response.body()!!.isEmpty())
                {
                    view!!.findViewById<TextView>(R.id.tv_notFound_frag_feed).visibility = View.VISIBLE
                }
                val videosAdapter =  VideosAdapter(context, response.body())
                val linearLayoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, true)
                linearLayoutManager.stackFromEnd = true
                videos_rv.layoutManager = linearLayoutManager
                videos_rv.adapter = videosAdapter


                loader.dismissAlertDialog()
            }

            override fun onFailure(call: Call<List<VideosModel>>, t: Throwable) {
                Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
                loader.dismissAlertDialog()
            }

        })
    }
}