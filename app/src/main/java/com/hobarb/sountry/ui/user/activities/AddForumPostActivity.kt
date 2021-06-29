package com.hobarb.sountry.ui.user.activities

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import com.google.gson.JsonObject
import com.hobarb.sountry.R
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddForumPostActivity : AppCompatActivity() {
    lateinit var categoriesSpinner : Spinner

    lateinit var userNameEt:EditText
    lateinit var userPhoneEt:EditText
    lateinit var postDescriptionEt:EditText
    lateinit var uploadBtn:AppCompatButton
    
    lateinit var userName :String
    lateinit var userPhone :String
    lateinit var category: String
    lateinit var postDescription :String
    lateinit var loader: Loader



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_forum_post)

        loader = Loader(this)
        userNameEt = findViewById(R.id.et_userName_ac_addPostForum)
        userPhoneEt = findViewById(R.id.et_userPhone_ac_addPostForum)
        postDescriptionEt = findViewById(R.id.et_postDescription_ac_addPostForum)
        uploadBtn = findViewById(R.id.btn_upload_ac_addPostForum)

        categoriesSpinner = findViewById(R.id.sp_categories_ac_addForumPost)

        val arrayAdapter = ArrayAdapter<String>(applicationContext, R.layout.style_spinner, constants.forum_categories)
        arrayAdapter.setDropDownViewResource(R.layout.style_spinner)
        categoriesSpinner.adapter = (arrayAdapter)

        categoriesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                category =  constants.forum_categories[position]
            }


        }

        uploadBtn.setOnClickListener {
            loader.showAlertDialog()
            if(userNameEt.text.toString().isNullOrEmpty())
                userNameEt.setError("Enter your name")
            else if(userPhoneEt.text.toString().isNullOrEmpty())
                userPhoneEt.setError("Enter your contact number")
            else if(postDescriptionEt.text.toString().isNullOrEmpty())
                postDescriptionEt.setError("Enter post description")
            else{
                userName = userNameEt.text.toString()
                userPhone = userPhoneEt.text.toString()
                postDescription = postDescriptionEt.text.toString()

                val jsonObject = JsonObject()
                jsonObject.addProperty("user_id", SharedPrefs(applicationContext).readPrefs(constants.USER_ID_KEY))
                jsonObject.addProperty("user_name", userName)
                jsonObject.addProperty("user_phone", userPhone)
                jsonObject.addProperty("post_requirement", category)
                jsonObject.addProperty("post_description", postDescription)

                Toaster.showToast(applicationContext, jsonObject.toString() )
               callApi(jsonObject)



            }

        }



    }

    private fun callApi(jsonObject: JsonObject) {
        val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val call: Call<JsonObject>? = service.postAddForumPost(jsonObject)
        call!!.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                loader.dismissAlertDialog()
                //startActivity(Intent(applicationContext, FeedFragment::class.java))
                Toaster.showToast(applicationContext, "Post uploaded")
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                loader.dismissAlertDialog()
                Toaster.showToast(applicationContext, t.message.toString())
            }
        })
    }
}