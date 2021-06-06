package com.hobarb.sountry.ui.user.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hobarb.sountry.R
import com.hobarb.sountry.adapters.ConnectionsAdapter
import com.hobarb.sountry.apiHandler.ApiServices
import com.hobarb.sountry.apiHandler.RetrofitInstance
import com.hobarb.sountry.models.NotificationsModel
import com.hobarb.sountry.utilities.SharedPrefs
import com.hobarb.sountry.utilities.constants
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [ConnectionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectionsFragment : Fragment(R.layout.fragment_connections) {

    lateinit var connections_rv:RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loader = Loader(context)
        loader.showAlertDialog()
        connections_rv = view.findViewById(R.id.rv_connections_frag_conn)
        val user_id = SharedPrefs(context).readPrefs(constants.USER_ID_KEY).toLong()
        val service: ApiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val call: Call<List<NotificationsModel>>? = service.getConnections(user_id)
        call!!.enqueue(object : Callback<List<NotificationsModel>> {
            override fun onResponse(
                call: Call<List<NotificationsModel>>,
                response: Response<List<NotificationsModel>>
            ) {

                if(response.body()!!.isEmpty())
                {
                    view!!.findViewById<TextView>(R.id.tv_notFound_frag_conn).visibility = View.VISIBLE
                }
                val connectionsAdapter =  ConnectionsAdapter(context, response.body())
                val linearLayoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                connections_rv.layoutManager = linearLayoutManager
                connections_rv.adapter = connectionsAdapter

                loader.dismissAlertDialog()
            }
            override fun onFailure(call: Call<List<NotificationsModel>>, t: Throwable) {
                Toaster.showToast(context, ""+t.message)
                loader.dismissAlertDialog()
            }

        })


        }
}