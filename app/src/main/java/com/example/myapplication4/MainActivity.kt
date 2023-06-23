package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication4.ui.theme.MyApplication4Theme

class MainActivity : ComponentActivity() {
    private var serviceActive = false
    private lateinit var button: Button
    private var startServiceText = "Запустить сервис"
    private var stopServiceText = "Остановить сервис"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        button.text = startServiceText
        button.setOnClickListener() {
            serviceActive = !serviceActive
            val serviceIntent = Intent(this, MyService::class.java)
            serviceIntent.putExtra("active", serviceActive)
            startService(serviceIntent)
            if (serviceActive)
                button.text = stopServiceText
            else
                button.text = startServiceText
        }
    }
}
