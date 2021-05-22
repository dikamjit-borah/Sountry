 package com.hobarb.sountry.ui.user.activities

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hobarb.sountry.R
import com.hobarb.sountry.ui.user.fragments.ConnectionsFragment
import com.hobarb.sountry.ui.user.fragments.FeedFragment
import com.hobarb.sountry.ui.user.fragments.NotificationsFragment
import com.hobarb.sountry.ui.user.fragments.ProfileFragment


class DashboardActivity : AppCompatActivity() {
    private val REQUEST_VIDEO_CAPTURED:Int  = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val bottomNavBar = findViewById<View>(R.id.bottom_nav_ac_dash);

        val feedFragment = FeedFragment()
        val connectionsFragment = ConnectionsFragment()
        val notificationsFragment = NotificationsFragment()
        val profileFragment = ProfileFragment()

        changeFragment(feedFragment, resources.getString(R.string.feed)); //default page of user dashboard
        bottomNavBar.findViewById<LinearLayout>(R.id.iv_feed_btb).setOnClickListener {
            changeFragment(feedFragment, resources.getString(R.string.feed))
        }

        bottomNavBar.findViewById<LinearLayout>(R.id.iv_connections_btb).setOnClickListener {
            changeFragment(connectionsFragment, resources.getString(R.string.connections))
        }

        bottomNavBar.findViewById<LinearLayout>(R.id.iv_notifications_btb).setOnClickListener {
            changeFragment(notificationsFragment, resources.getString(R.string.notifications))
        }

        bottomNavBar.findViewById<LinearLayout>(R.id.iv_profile_btb).setOnClickListener {
            changeFragment(profileFragment, resources.getString(R.string.profile))
        }

        bottomNavBar.findViewById<View>(R.id.cv_video_bnb).setOnClickListener {

            startActivity(Intent(this@DashboardActivity, UploadActivity::class.java))

        }


    }


    private fun changeFragment(fragment: Fragment, toolbarTitle: String) {
        supportActionBar!!.title = toolbarTitle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragments_ac_dash, fragment).commit()
        }

    }


}