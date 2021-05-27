package com.hobarb.sountry.ui.user.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hobarb.sountry.R
import com.hobarb.sountry.adapters.VideosAdapter
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.models.VideosModel
import com.hobarb.sountry.utilities.views.Loader
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
        loader.showAlertDialog()
        val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val call: Call<List<VideosModel>>? = service.videos

        call!!.enqueue(object : Callback<List<VideosModel>>{
            override fun onResponse(
                call: Call<List<VideosModel>>,
                response: Response<List<VideosModel>>
            ) {


                val videosAdapter =  VideosAdapter(context, response.body())
                val linearLayoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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