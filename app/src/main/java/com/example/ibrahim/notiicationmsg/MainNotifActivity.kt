package com.example.ibrahim.notiicationmsg

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
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
        registerReceiver(receiver, filter)

        val intent = Intent()
        intent.action =Intent.ACTION_VIEW
        sendBroadcast(intent)

        pushBtn.setOnClickListener {
            if (pushtxtmsg.text.isEmpty()) Toast.makeText(this, "Please Write Some Content first", Toast.LENGTH_SHORT).show()
            else {
                val pushNotifBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, "ChannelId 1")
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Simple Notification")
                        .setContentText(pushtxtmsg.text.toString())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(1, pushNotifBuilder.build())
            }
        }

    }

    override fun onResume() {
        registerReceiver(receiver, filter)
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)

    }


}
