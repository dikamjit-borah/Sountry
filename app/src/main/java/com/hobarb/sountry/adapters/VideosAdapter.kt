package com.hobarb.sountry.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.hobarb.sountry.R
import com.hobarb.sountry.models.VideosModel
import com.hobarb.sountry.ui.user.activities.VideoDetailActivity

class VideosAdapter(var context: Context?, videoModels: List<VideosModel>?) : RecyclerView.Adapter<VideosAdapter.VideosAdapterViewHolder>(){

    var videoModels:List<VideosModel>

    init {
        this.videoModels = videoModels!!
        this.context = context;

    }

    inner class  VideosAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var creatorId_tv: TextView
        var datetime_tv: TextView
        var username_tv: TextView
        var video_wv: WebView
        var connect_btn: AppCompatButton

        init {
            creatorId_tv = itemView.findViewById(R.id.tv_creatorId_ca_feed_video);
            datetime_tv = itemView.findViewById(R.id.tv_dateCreated_ca_feed_video);
            video_wv = itemView.findViewById(R.id.wv_video_ca_feed_video);
            username_tv = itemView.findViewById(R.id.tV_username_ca_feed_video);
            connect_btn = itemView.findViewById(R.id.btn_connect_ca_feed_video);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosAdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_feed_video, parent, false)
        return VideosAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideosAdapterViewHolder, position: Int) {
        holder.creatorId_tv.setText(""+videoModels[position].videoCreatorId)
        holder.datetime_tv.setText(""+videoModels[position].videoDateCreated)
        holder.video_wv.loadUrl(videoModels[position].videoUrl)
        holder.username_tv.setText(""+videoModels[position].videoId)
        holder.connect_btn.setOnClickListener {

            val intent = Intent(context, VideoDetailActivity::class.java)

            intent.putExtra("video_id", "" + videoModels[position].videoId)
            intent.putExtra("video_creator_id", "" + videoModels[position].videoCreatorId)
            intent.putExtra("video_date", "" + videoModels[position].videoDateCreated)
            intent.putExtra("video_url", "" + videoModels[position].videoUrl)

            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videoModels.size
    }


}
