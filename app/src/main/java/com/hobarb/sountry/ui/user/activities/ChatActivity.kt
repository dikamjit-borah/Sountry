package com.hobarb.sountry.ui.user.activities

import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.hobarb.sountry.R
import com.hobarb.sountry.adapters.MessageAdapter
import com.hobarb.sountry.adapters.VideosAdapter
import com.hobarb.sountry.models.MessageModel
import com.hobarb.sountry.utilities.views.Loader
import com.hobarb.sountry.utilities.views.Toaster
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ChatActivity : AppCompatActivity() {

    private lateinit var list: ArrayList<MessageModel>
    lateinit var mDatabase: DatabaseReference;
    lateinit var messages_rv:RecyclerView
    lateinit var roomId:String
    lateinit var loader: Loader
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val msg_et = findViewById<EditText>(R.id.et_msg_ac_chat)
        val send_btn = findViewById<ImageView>(R.id.iv_send_ac_chat)
        messages_rv = findViewById(R.id.rv_messages_ac_chat)

        val user1 = intent.getStringExtra("user_id_1")
        val user2 = intent.getStringExtra("user_id_2")

        val probableRoom1 = user1+user2;
        val probableRoom2 = user2+user1;

        loader = Loader(this@ChatActivity)


        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        val now: LocalDateTime = LocalDateTime.now()
        val dateTime = (dtf.format(now))


        mDatabase = FirebaseDatabase.getInstance().getReference();

        list = arrayListOf()

        loader.showAlertDialog()

        mDatabase.child(probableRoom2).addListenerForSingleValueEvent(object:ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            if(snapshot.exists())
            {
                roomId = probableRoom2
                setAdapter()
            }
            else
            {
                mDatabase.child(probableRoom1).addListenerForSingleValueEvent(object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        roomId = probableRoom1
                        setAdapter()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }


                } )
            }


         }

         override fun onCancelled(error: DatabaseError) {
          TODO("Not yet implemented")
        }

  })



        send_btn.setOnClickListener {
            if(msg_et.text.isNullOrEmpty())
                msg_et.setError("Write your message")
            else{

                val message = MessageModel(
                    user1!!.toLong(),
                    msg_et.text.toString(),
                    dateTime
                );
                val timestamp = System.currentTimeMillis().toString();

                mDatabase.child(roomId).child(timestamp).setValue(message)

            }

        }


    }

    private fun setAdapter() {
        mDatabase.child(roomId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (snapshot1 in snapshot.children) {

                    val userId = snapshot1.child("userId").value.toString().toLong()
                    val dateTime = snapshot1.child("datetime").value.toString()
                    val message = snapshot1.child("message").value.toString()
                    val msg = MessageModel(userId, message, dateTime)

                    list.add(msg)
                }
                val messageAdapter =  MessageAdapter(applicationContext, list)
                val linearLayoutManager =
                    LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
                messages_rv.layoutManager = linearLayoutManager
                messages_rv.adapter = messageAdapter
                messageAdapter.notifyDataSetChanged()
                loader.dismissAlertDialog()
            }

            override fun onCancelled(error: DatabaseError) {
                throw error.toException()
            }
        })

    }
}