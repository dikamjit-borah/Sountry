package com.hobarb.sountry.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.hobarb.sountry.R
import com.hobarb.sountry.models.ForumPostModel


class ForumAdapter (var context: Context?, forumPosts: ForumPostModel?) : RecyclerView.Adapter<ForumAdapter.ForumAdapterViewHolder>(){

    var forumPosts:ForumPostModel

    init {
        this.forumPosts = forumPosts!!
        this.context = context;

    }

    inner class  ForumAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var userName_tv: TextView
        var userPhone_tv: TextView
        var postRequirement_tv: TextView
        var postDescription_tv: TextView




        init {
            userName_tv = itemView.findViewById(R.id.tv_userName_ca_forumPost);
            userPhone_tv = itemView.findViewById(R.id.tv_userPhone_ca_forumPost);
            postRequirement_tv = itemView.findViewById(R.id.tv_postRequirement_ca_forumPost);
            postDescription_tv = itemView.findViewById(R.id.tv_postDescription_ca_forumPost);




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumAdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_forum_post, parent, false)
        return ForumAdapterViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ForumAdapterViewHolder, position: Int) {

        holder.userName_tv.setText(""+forumPosts.data[position].userName)
        holder.userPhone_tv.setText(""+forumPosts.data[position].userPhone)
        holder.postRequirement_tv.setText(""+forumPosts.data[position].postRequirement)
        holder.postDescription_tv.setText(""+forumPosts.data[position].postDescription)


    }

    override fun getItemCount(): Int {
        return forumPosts.data.size
    }


}
