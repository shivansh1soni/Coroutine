package com.example.coroutinetest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var btnShowMessage: Button
    private lateinit var btnStartTask: Button
    private lateinit var textCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnShowMessage = findViewById(R.id.btn_show_msg)
        btnStartTask = findViewById(R.id.btn_start_task)
        textCount = findViewById(R.id.textView)

        btnShowMessage.setOnClickListener {
            // show a message
            Toast.makeText(this, "Hello World!", Toast.LENGTH_SHORT).show()
        }

        btnStartTask.setOnClickListener {
            // start some long running task
            // which can potentially block the UI thread

            lifecycleScope.launch(Dispatchers.IO) {
                for (i in 1..100000000) {
                    withContext(Dispatchers.Main) {
                        textCount.text = i.toString()

                    }
                }

            }
        }

    }
}