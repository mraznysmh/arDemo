package com.example.ardemo.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ardemo.R
import com.example.ardemo.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cameraButton: Button = findViewById(R.id.cameraButton)

        cameraButton.setOnClickListener {
            val arView= Intent(this, arActivity::class.java)

            startActivity(arView)
        }
    }

}

