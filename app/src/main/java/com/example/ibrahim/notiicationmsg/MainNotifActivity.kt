package com.example.ibrahim.notiicationmsg

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.Intent.*
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_notif.*

class MainNotifActivity : AppCompatActivity() {

    private var receiver: BroadcastReceiver? = null
    private val filter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_notif)
        receiver = MyReceiver()

        filter.addAction(ACTION_AIRPLANE_MODE_CHANGED)
        filter.addAction(ACTION_POWER_DISCONNECTED)
        filter.addAction(ACTION_POWER_CONNECTED)
        filter.addAction(ACTION_SCREEN_ON)
        filter.addAction(ACTION_SCREEN_OFF)
        filter.addAction(ACTION_BATTERY_LOW )
        filter.addAction("Custom Action")
        registerReceiver(receiver, filter)


        pushBtn.setOnClickListener {
            if (pushtxtmsg.text.isEmpty()) Toast.makeText(this, "Please Write Some Content first", Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent()
                intent.action = "Custom Action"
                intent.putExtra("CustomText", pushtxtmsg.text.toString())
                Log.i("CustTxt", pushtxtmsg.text.toString())
                sendBroadcast(intent)
                pushtxtmsg.setText("")


            }
        }
    }

    override fun onResume() {
        registerReceiver(receiver, filter)
        super.onResume()
        Log.i("Resume", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }
}
