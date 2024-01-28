package com.wilmar.firstapp.FirsApp.First

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.wilmar.firstapp.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult:TextView = findViewById(R.id.tvResult)
        //tomando el nombre de los extra
        val name : String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        tvResult.text=name
    }
}