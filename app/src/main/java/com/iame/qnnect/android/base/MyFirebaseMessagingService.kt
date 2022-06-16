package com.iame.qnnect.android.base

import android.annotation.SuppressLint
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
import android.graphics.Color
import android.net.Uri
import android.os.PowerManager
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
import com.iame.qnnect.android.MyApplication
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.splash.SplashActivity


// MyFirebaseMessagingService.class
class MyFirebaseMessagingService : FirebaseMessagingService() {

    var baseToken = BaseToken()
    private val TAG = "Firebase"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("response!!", "Refreshed token: $token")
    }

    //onMessageReceived : 받은 메시지에서 title과 body를 추출
    @SuppressLint("InvalidWakeLockTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("fcm_response!!", remoteMessage.toString())
        Log.d("fcm_response!!", remoteMessage.data.toString())
        Log.d("fcm_response!!", remoteMessage.notification.toString())

        // 화면 깨우기
        val pm = getSystemService(POWER_SERVICE) as PowerManager
        val wakeLock = pm.newWakeLock(FLAG_KEEP_SCREEN_ON, TAG)
        wakeLock.acquire(3000)

        // Data 항목이 있을때.
        // background 처리시
        // {
        //      body=아아아아아아아아아아,
        //      link=https://iame.page.link/Fc4u,
        //      image=,
        //      title=📮내 답변에 댓글이 달렸어요! 댓글을 보러 가볼까요?
        // }

        if(remoteMessage.data.isNotEmpty()){
            val data = remoteMessage.data
            val messageTitle = data.getValue("title")
            val messageBody = data.getValue("body")
            Log.d("fcm_response!!", "$messageTitle $messageBody")
            sendMessage(messageTitle!!, messageBody!!)
        }

        // foreground 처리시
        Log.d("fcm_response", "data 알림 메시지: " + remoteMessage.data.toString())
        if (remoteMessage.notification != null) {
            val messageBody = remoteMessage.notification!!.body
            val messageTitle = remoteMessage.notification!!.title
            Log.d("fcm_response!!", "$messageTitle $messageBody")
            sendMessage(messageTitle!!, messageBody!!)
        }
    }

    private fun sendMessage(messageTitle: String, messageBody: String) {
        val intent = Intent(this, SplashActivity::class.java)
        intent.putExtra("title", messageTitle)
        intent.putExtra("body", messageBody)
        intent.putExtra("alarm", true)
        baseToken.setAlarm(this, true)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val channelId = "Alarm"

        val defaultSoundUri: Uri =
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL)
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
            val channelName = "Qnnect"
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