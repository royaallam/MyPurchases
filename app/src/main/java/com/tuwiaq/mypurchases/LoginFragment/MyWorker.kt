package com.tuwiaq.mypurchases.LoginFragment

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tuwiaq.mypurchases.R

private const val TAG = "MyWorker"

// class MyWorker(context:Context, workerParameters: WorkerParameters):
// Worker(context,workerParameters) {
//     override fun doWork(): Result {
//         Log.d("do work succcess", "doWork: success fuction called ")
//         showNotification()
//         return Result.success()
//     }
//
//     private fun showNotification() {
//         val intent=Intent(applicationContext,LoginFragment::class.java).apply {
//             flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//         }
//         val pendingIntent=PendingIntent.getActivities(
//             applicationContext,0,intent,0
//         )
//
//         val builder= NotificationCompat.Builder(applicationContext,"my_unique_id")
//             .setSmallIcon(R.drawable.image_person)
//             .setContentTitle("Life is good")
//             .setContentTitle("Subscribe on the channel")
//             .setAutoCancel(true)
//             .setContentIntent(pendingIntent)
//
//
//
//         with(NotificationManagerCompat.from(applicationContext)){
//             notify(1,builder.build())
//         }
//     }

// }