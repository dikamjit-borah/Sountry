package com.hobarb.sountry.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.hobarb.sountry.R
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.models.NotificationsModel
import com.hobarb.sountry.ui.user.activities.ChatActivity
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.views.InflaterFunctions
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConnectionsAdapter (var context: Context?, notificationModels: List<NotificationsModel>?) : RecyclerView.Adapter<ConnectionsAdapter.ConnectionsAdapterViewHolder>(){

    var notificationModels:List<NotificationsModel>

    init {
        this.notificationModels = notificationModels!!
        this.context = context;

    }

    inner class  ConnectionsAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var userId_tv: TextView
        var userName_tv: TextView
        var parent_cv: CardView
        var ll_genresParent: LinearLayout
        


        init {
            userId_tv = itemView.findViewById(R.id.tv_userId_ca_conn);
            userName_tv = itemView.findViewById(R.id.tv_userName_ca_conn);
            ll_genresParent = itemView.findViewById(R.id.ll_genresParent_ca_conn);
            parent_cv = itemView.findViewById(R.id.cv_parent_ca_conn);




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionsAdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_connection, parent, false)
        return ConnectionsAdapterViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ConnectionsAdapterViewHolder, position: Int) {

        holder.userId_tv.setText(""+notificationModels[position].up_user_id)
        holder.userName_tv.setText(""+notificationModels[position].up_user_name)
        val listOfGenres = Gson().fromJson( notificationModels[position].up_preferred_genres, mutableListOf<String>().javaClass)

        InflaterFunctions.inflateGenres(context!!,holder.ll_genresParent, listOfGenres)
        holder.parent_cv.setOnClickListener {
            val user_id_1 = SharedPrefs(context).readPrefs(constants.USER_ID_KEY).toLong()
            val user_id_2 = notificationModels[position].up_user_id.toLong()
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("user_id_1", user_id_1.toString())
            intent.putExtra("user_id_2", user_id_2.toString())
            //Toaster.showToast(applicationContext, user_id.toString())
           context!!.startActivity(intent)
        }
        
    }

    override fun getItemCount(): Int {
        return notificationModels.size
    }


}
