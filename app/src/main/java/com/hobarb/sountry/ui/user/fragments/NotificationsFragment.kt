package com.hobarb.sountry.ui.user.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.hobarb.sountry.R
import com.hobarb.sountry.adapters.NotificationsAdapter
import com.hobarb.sountry.adapters.VideosAdapter
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.models.NotificationsModel
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    lateinit var notifications_rv:RecyclerView

    lateinit var loader: Loader
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loader = Loader(context)
        notifications_rv = view.findViewById(R.id.rv_notifications_frag_noti)
        loader.showAlertDialog()
        val user_id = SharedPrefs(context).readPrefs(constants.USER_ID_KEY).toLong()

        val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val call: Call<List<NotificationsModel>>? = service.getNotifications(user_id)
        call!!.enqueue(object : Callback<List<NotificationsModel>> {
            override fun onResponse(
                call: Call<List<NotificationsModel>>,
                response: Response<List<NotificationsModel>>
            ) {

                val notificationsAdapter =  NotificationsAdapter(context, response.body())
                val linearLayoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                notifications_rv.layoutManager = linearLayoutManager
                notifications_rv.adapter = notificationsAdapter

                loader.dismissAlertDialog()
            }

            override fun onFailure(call: Call<List<NotificationsModel>>, t: Throwable) {
                Toaster.showToast(context, ""+t.message)
                loader.dismissAlertDialog()
            }

        })
    }
}