package com.hobarb.sountry.ui.user.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hobarb.sountry.R
import com.hobarb.sountry.adapters.ForumAdapter
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.models.ForumPostModel
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForumActivity : AppCompatActivity() {
    lateinit var forumPostsRv:RecyclerView
    lateinit var loader:Loader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)

        forumPostsRv = findViewById(R.id.rv_forumPosts_ac_forum)

        findViewById<FloatingActionButton>(R.id.fab_addPost_ac_forum).setOnClickListener {
            startActivity(Intent(this, AddForumPostActivity::class.java))
        }
         loader = Loader(this@ForumActivity)
        
    }

    override fun onResume() {
        super.onResume()
        fetchPosts()
    }

    private fun fetchPosts() {
        loader.showAlertDialog()
        val service: ApiServices =
            RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val call: Call<ForumPostModel>? = service.forumPosts
        call!!.enqueue(object : Callback<ForumPostModel> {
            override fun onResponse(
                call: Call<ForumPostModel>,
                response: Response<ForumPostModel>
            ) {
                Toaster.showToast(applicationContext, ""+response.body()!!.status)
                loadForumPostsIntoRv(response.body())
                loader.dismissAlertDialog()
            }

            override fun onFailure(call: Call<ForumPostModel>, t: Throwable) {

                Toaster.showToast(applicationContext, t.message.toString())
                loader.dismissAlertDialog()
            }

        })
    }

    private fun loadForumPostsIntoRv(data: ForumPostModel?) {
        val forumAdapter =  ForumAdapter(applicationContext, data)
        val linearLayoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        forumPostsRv.layoutManager = linearLayoutManager
        forumPostsRv.adapter = forumAdapter
    }
}