package com.example.myapplication4

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class MyService : Service() {
    private val TAG = "MyService"
    private val DELAY_MILLIS: Long = 10000
    private var needContinue = false
    private lateinit var handler: Handler
    private lateinit var logRunnable: Runnable

    override fun onCreate() {
        super.onCreate()
        handler = Handler(Looper.getMainLooper())
        logRunnable = object : Runnable {
            override fun run() {
                if (needContinue) {
                    Log.d(TAG, "Сервис активен")
                    handler.postDelayed(this, DELAY_MILLIS)
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Сервис уничтожен")
        handler.removeCallbacks(logRunnable)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int):
            Int {
        Log.d(TAG, "Сервис получил команду")
        if (intent != null) {
            needContinue = intent.getBooleanExtra("active", false)
            handler.postDelayed(logRunnable, 0)
        }
        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}