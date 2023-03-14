package com.example.scrutinizing_the_service.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class FirstJIService : JobIntentService() {

    companion object {
        const val TAG = "FirstJIService"
        const val SOME_JOB_ID = 13

        fun enqueueWork(
            context: Context,
            intent: Intent
        ) {
            enqueueWork(context, FirstJIService::class.java, SOME_JOB_ID, intent)
        }
    }

    private var randomNumber = 1
    private var isRandomNumberGeneratorOn = false

    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, intent.action.toString())
        Log.d(TAG, " ${intent.data}")
        Log.d(TAG, "${intent.extras}")

        isRandomNumberGeneratorOn = true
        startRandomNumberGenerator()

    }

    private fun startRandomNumberGenerator() {
        while (isRandomNumberGeneratorOn) {
            Thread.sleep(1000)
            if(isRandomNumberGeneratorOn) {
                randomNumber = (0..1000).random()
                Log.d(TAG, "Current random number: $randomNumber")
            } else {
                Log.d(TAG, "Random number generation stopped")
            }
        }
    }

    override fun onStopCurrentWork(): Boolean {
        Log.d(TAG, "onStopCurrentWork")
        return super.onStopCurrentWork()
    }


}