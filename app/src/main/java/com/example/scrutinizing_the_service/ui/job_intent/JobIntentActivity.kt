package com.example.scrutinizing_the_service.ui.job_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scrutinizing_the_service.R
import com.example.scrutinizing_the_service.databinding.ActivityJobIntentBinding
import com.example.scrutinizing_the_service.services.FirstJIService

class JobIntentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        with(binding) {
            btnStartService.setOnClickListener {
                startService()
            }

            btnStopService.setOnClickListener {
                stopService()
            }
        }
    }

    private fun startService() {
        FirstJIService.enqueueWork(
            this,
            Intent(
                this,
                FirstJIService::class.java
            )
        )
    }

    private fun stopService() {

    }

}