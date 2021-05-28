package com.hobarb.sountry.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.hobarb.sountry.R
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.models.NotificationsModel
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.views.InflaterFunctions
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsAdapter (var context: Context?, notificationModels: List<NotificationsModel>?) : RecyclerView.Adapter<NotificationsAdapter.NotificationsAdapterViewHolder>(){

    var notificationModels:List<NotificationsModel>

    init {
        this.notificationModels = notificationModels!!
        this.context = context;

    }

    inner class  NotificationsAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var userId_tv: TextView
        var userName_tv: TextView
        var ll_genresParent: LinearLayout

        var accept_btn:AppCompatButton
        var reject_btn:AppCompatButton


        init {
            userId_tv = itemView.findViewById(R.id.tv_userId_ca_noti);
            userName_tv = itemView.findViewById(R.id.tv_userName_ca_noti);
            ll_genresParent = itemView.findViewById(R.id.ll_genresParent_ca_noti);
            accept_btn = itemView.findViewById(R.id.btn_accept_ca_noti);
            reject_btn = itemView.findViewById(R.id.btn_reject_ca_noti);


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsAdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_notification, parent, false)
        return NotificationsAdapterViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NotificationsAdapterViewHolder, position: Int) {

        holder.userId_tv.setText(""+notificationModels[position].up_user_id)
        holder.userName_tv.setText(""+notificationModels[position].up_user_name)
        val listOfGenres = Gson().fromJson( notificationModels[position].up_preferred_genres, mutableListOf<String>().javaClass)

        InflaterFunctions.inflateGenres(context!!,holder.ll_genresParent, listOfGenres)
        holder.accept_btn.setOnClickListener {
            val loader = Loader(context)
            loader.showAlertDialog()

            val connect_with_id = SharedPrefs(context).readPrefs(constants.USER_ID_KEY).toLong()
            val request_by_id = notificationModels[position].up_user_id.toLong()
            val is_connected:Int = 1
            val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
            val call: Call<JsonObject>? = service.updateConnection(connect_with_id, request_by_id, is_connected)
            call!!.enqueue(object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Toaster.showToast(context, ""+response.body())
                    loader.dismissAlertDialog()
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toaster.showToast(context, ""+t.message)
                    loader.dismissAlertDialog()
                }

            })


            holder.accept_btn.text = "Request accepted"
            holder.accept_btn.setBackgroundColor(context!!.getColor(R.color.primaryLight))
            holder.reject_btn.visibility = View.GONE
        }
        holder.reject_btn.setOnClickListener {
            val loader = Loader(context)
            loader.showAlertDialog()
            val connect_with_id = SharedPrefs(context).readPrefs(constants.USER_ID_KEY).toLong()
            val request_by_id = notificationModels[position].up_user_id.toLong()
            val is_connected:Int = 0


            val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
            val call: Call<JsonObject>? = service.updateConnection(connect_with_id, request_by_id, is_connected)
            call!!.enqueue(object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Toaster.showToast(context, ""+response.body())
                    loader.dismissAlertDialog()
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toaster.showToast(context, ""+t.message)
                    loader.dismissAlertDialog()
                }

            })


            holder.reject_btn.text = "Connection rejected"
            holder.reject_btn.setBackgroundColor(context!!.getColor(R.color.primaryLight))
            holder.accept_btn.visibility = View.GONE

        }
    }

    override fun getItemCount(): Int {
        return notificationModels.size
    }


}
