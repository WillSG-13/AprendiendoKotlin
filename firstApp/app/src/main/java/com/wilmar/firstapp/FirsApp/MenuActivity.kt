package com.wilmar.firstapp.FirsApp

import android.content.BroadcastReceiver
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.wilmar.firstapp.FirsApp.First.activity_First
import com.wilmar.firstapp.FirsApp.SuperHeroApp.SuperHeroListActivity
import com.wilmar.firstapp.FirsApp.ToDoApp.ToDoActivity
import com.wilmar.firstapp.FirsApp.imccalculator.ImcActivity
import com.wilmar.firstapp.R

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val buttonSaludar = findViewById<Button>(R.id.btnSaludar)
        val buttonImcApp = findViewById<Button>(R.id.btnImcpp)
        val btnToDo = findViewById<Button>(R.id.btnToDo)
        val btnSuperHero = findViewById<Button>(R.id.btnSuperHero)
        buttonSaludar.setOnClickListener { navigateToSalidar() }
        buttonImcApp.setOnClickListener { navigateToImcApp() }
        btnToDo.setOnClickListener { navigateToToDo() }
        btnSuperHero.setOnClickListener { navigateTosuperHero() }

    }

    private fun navigateTosuperHero() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToToDo() {
        val intent = Intent(this, ToDoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToImcApp() {
        val intent = Intent(this, ImcActivity::class.java);
        startActivity(intent)
    }

    private fun navigateToSalidar() {
        val intent = Intent(this, activity_First::class.java)
        startActivity(intent)
    }
}