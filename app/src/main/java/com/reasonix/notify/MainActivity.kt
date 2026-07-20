package com.reasonix.notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()

        val title = intent.getStringExtra("title")
        val message = intent.getStringExtra("message")

        // 只有通过 ADB 传了参数才发通知，直接点图标打开时静默
        if (title != null || message != null) {
            val notifyId = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title ?: "来自电脑的消息")
                .setContentText(message ?: "(空)")
                .setStyle(NotificationCompat.BigTextStyle().bigText(message ?: "(空)"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .build()

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(notifyId, notification)
        }

        finish()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "手机弹窗",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "从电脑推送到手机的通知"
                enableVibration(true)
                enableLights(true)
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "pc_notify"
    }
}

