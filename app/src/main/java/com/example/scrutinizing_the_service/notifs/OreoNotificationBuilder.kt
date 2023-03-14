package com.example.scrutinizing_the_service.notifs

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.scrutinizing_the_service.R
import com.example.scrutinizing_the_service.ui.launcher.MainActivity

class OreoNotificationBuilder(
    val context: Context
) : NotificationBuilder {

    companion object {
        const val CHANNEL_1_ID = "CHANNEL_1_ID"
        const val CHANNEL_2_ID = "CHANNEL_2_ID"
        const val REQUEST_CODE = 100
    }

    private val notificationManager by lazy {
        NotificationManagerCompat.from(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createNotification(context: Context) {
        // Pending intent is passed on the to the click listener
        // of the notification. This is a wrapper around
        // normal intents. We can set more properties to
        // configure the actions on it.
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, REQUEST_CODE, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.placeholder)
            .setContentTitle("My First Notification Title")
            .setContentText("First Notification Description")
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    Resources.getSystem(),
                    R.drawable.placeholder
                )
            )
            // TODO See code examples from medium to how to add image files to
            // TODO notifications on devices of Android OReo or above.
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                            " when an unknown printer took a galley of type and scrambled it to make a type specimen book." +
                            " It has survived not only five centuries, but also the leap into electronic typesetting," +
                            " remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset" +
                            " sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like" +
                            " Aldus PageMaker including versions of Lorem Ipsum."
                ).setBigContentTitle("Expanded Title")
                    .setSummaryText("This is the summary test. Lets see where this goes.")
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setColor(ContextCompat.getColor(context, R.color.purple_200))
            .build()
        notificationManager.notify(1, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createChannel(context: Context) {
        val channel1 = NotificationChannel(
            CHANNEL_1_ID,
            "some_identifier_1",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel1.description = "This is First Notification Channel description"

        val channel2 = NotificationChannel(
            CHANNEL_2_ID,
            "some_identifier_2",
            NotificationManager.IMPORTANCE_LOW
        )
        channel2.description = "This is Second Notification Channel description"

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel1)
        manager.createNotificationChannel(channel2)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun redirectToSettings() {
        Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            context.startActivity(this)
        }
    }


}