package com.hobarb.sountry.ui.user.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import com.google.gson.Gson
import com.hobarb.sountry.R
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.models.ProfileModel
import com.hobarb.sountry.ui.login.LoginActivity
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.views.InflaterFunctions
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    lateinit var loader: Loader
    //lateinit var binding: FragmentProfileBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loader = Loader(context)
        loader.showAlertDialog()

        val user_id = SharedPrefs(context).readPrefs(constants.USER_ID_KEY).toLong()
        val logout = view.findViewById<AppCompatButton> (R.id.btn_logout_frag_prof)
        logout.setOnClickListener {
            SharedPrefs(context).writePrefs(constants.TOKEN_KEY, "")
            startActivity(Intent(context, LoginActivity::class.java))
            activity!!.finish()
        }

        val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val call: Call<List<ProfileModel>>? = service.getUserProfile(user_id)
        call!!.enqueue(object : Callback<List<ProfileModel>> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(call: Call<List<ProfileModel>>, response: Response<List<ProfileModel>>) {
                updateViews(response.body()!![0])
                loader.dismissAlertDialog()
            }

            override fun onFailure(call: Call<List<ProfileModel>>, t: Throwable) {
                Toaster.showToast(context, ""+ t.message)
                loader.dismissAlertDialog()
            }
        })



    }

    private fun updateViews(profileModel: ProfileModel) {

        view!!.findViewById<TextView>(R.id.tv_username_frag_prof).text = profileModel.up_user_name
        view!!.findViewById<TextView>(R.id.tv_userId_frag_prof).text = profileModel.up_user_id
        view!!.findViewById<TextView>(R.id.tv_fullname_frag_prof).text = profileModel.up_full_name
        view!!.findViewById<TextView>(R.id.tv_phone_frag_prof).text = profileModel.up_phone
        view!!.findViewById<TextView>(R.id.tv_email_frag_prof).text = profileModel.up_email
        view!!.findViewById<TextView>(R.id.tv_gender_frag_prof).text = profileModel.up_gender

        val listOfGenres = Gson().fromJson( profileModel.up_preferred_genres, mutableListOf<String>().javaClass)
        InflaterFunctions.inflateGenres(context!!,view!!.findViewById<LinearLayout>(R.id.ll_genresParent_frag_prof), listOfGenres)

        val listOfGender = Gson().fromJson( profileModel.up_preferred_gender, mutableListOf<String>().javaClass)
        InflaterFunctions.inflateGender(context!!,view!!.findViewById<LinearLayout>(R.id.ll_genderParent_frag_prof), listOfGender)



    }
}