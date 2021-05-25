package com.hobarb.sountry.ui.user.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hobarb.sountry.R
import com.hobarb.sountry.databinding.ActivityVideoDetailBinding

class VideoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val video_url: String? = intent.getStringExtra("video_url")
        val video_date: String? = intent.getStringExtra("video_date")

        binding.wvVideoAcVidDet.loadUrl(video_url.toString())
        binding.tvDateCreatedAcVidDet.setText(""+video_date)
    }
}