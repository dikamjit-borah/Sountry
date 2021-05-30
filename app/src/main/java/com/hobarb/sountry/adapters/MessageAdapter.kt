package com.hobarb.sountry.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.hobarb.sountry.R
import com.hobarb.sountry.models.MessageModel
import com.hobarb.sountry.utilities.views.InflaterFunctions

class MessageAdapter (var context: Context?, messageModels: List<MessageModel>?) : RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder>(){

    var messageModels:List<MessageModel>

    init {
        this.messageModels = messageModels!!
        this.context = context;

    }

    inner class  MessageAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var userId_tv: TextView
        var datetime_tv: TextView
        var msgText_tv: TextView



        init {
            userId_tv = itemView.findViewById(R.id.tv_userId_ca_msg);
            datetime_tv = itemView.findViewById(R.id.tv_dateTime_ca_msg);
            msgText_tv = itemView.findViewById(R.id.tv_msgText_ca_msg);



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_message, parent, false)
        return MessageAdapterViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MessageAdapterViewHolder, position: Int) {

        holder.userId_tv.setText(""+messageModels[position].userId)
        holder.datetime_tv.setText(""+messageModels[position].datetime)
        holder.msgText_tv.setText(""+messageModels[position].message)
    }

    override fun getItemCount(): Int {
        return messageModels.size
    }


}
