package com.iame.qnnect.android.base

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.media.RingtoneManager

import android.app.PendingIntent
import android.content.Context

import android.content.Intent
import android.net.Uri
import com.iame.qnnect.android.MyApplication
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.splash.SplashActivity


// MyFirebaseMessagingService.class
class MyFirebaseMessagingService : FirebaseMessagingService() {

    var baseToken = BaseToken()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("response!!", "Refreshed token: $token")
    }

    //onMessageReceived : 받은 메시지에서 title과 body를 추출
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            Log.d("FCM Log", "알림 메시지: " + remoteMessage.notification!!.body)
            val messageBody = remoteMessage.notification!!.body
            val messageTitle = remoteMessage.notification!!.title


            val intent = Intent(this, MyApplication::class.java)
            baseToken.setAlarm(this, true)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
            val channelId = "Channel ID"

            val defaultSoundUri: Uri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.img_logo_foreground)
                    .setContentTitle(messageTitle)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)

            val notificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelName = "Channel Name"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0, notificationBuilder.build())
        }
    }
}

// private const val CHANNEL_ID = "my_channel"
//
//class FirebaseService : FirebaseMessagingService() {
//
//    companion object {
//        var sharedPref: SharedPreferences? = null
//
//        var token: String?
//        get() {
//            return sharedPref?.getString("token", "")
//        }
//        set(value) {
//            sharedPref?.edit()?.putString("token", value)?.apply()
//        }
//    }
//
//    // 사용자 디바이스 토큰 새로 생성
//    override fun onNewToken(newToken: String) {
//        super.onNewToken(newToken)
//        token = newToken
//    }
//
//    //알람이 온 경우 --> 어떻게 보이게 할 것인지, 누르면 어디로 이동하게 할 것인지 정하는 메소드
//    override fun onMessageReceived(message: RemoteMessage) {
//        super.onMessageReceived(message)
//
//        //알림 팝업 누를 시 MainActivity로 이동
//        val intent = Intent(this, MainActivity::class.java)
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notificationID = Random.nextInt()
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createNotificationChannel(notificationManager)
//        }
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)
//        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle(message.data["title"])
//            .setContentText(message.data["message"])
//            .setSmallIcon(R.drawable.ic_cat_smile)
//            .setAutoCancel(true)
//            .setContentIntent(pendingIntent)
//            .build()
//
//        notificationManager.notify(notificationID, notification)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun createNotificationChannel(notificationManager: NotificationManager) {
//        val channelName = "channelName"
//        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
//            description = "My channel description"
//            enableLights(true)
//            lightColor = Color.GREEN
//        }
//        notificationManager.createNotificationChannel(channel)
//    }
//}