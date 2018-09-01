package com.example.ibrahim.notiicationmsg


import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

         Toast.makeText(context,"Action: " +intent?.action,Toast.LENGTH_LONG).show()
        val pushNotifBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, "ChannelId 1")
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Simple Notification")
                        .setContentText(intent?.action)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(1, pushNotifBuilder.build())
    }
}