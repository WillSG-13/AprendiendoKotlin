package com.wilmar.firstapp.FirsApp.First

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.wilmar.firstapp.R

class activity_First : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        //el boton
        val button = findViewById<AppCompatButton>(R.id.button)
        //texto de la tarjeta
        val text = findViewById<TextView>(R.id.texto)
        //texto del textEdit
        val edText =findViewById<EditText>(R.id.edText)

        button.setOnClickListener{
            //Log.i("wilmar","se Pulso Esa M")
            //texto tomado del textEdit
            var nombre =edText.text.toString()

            if(nombre.isNotEmpty()){
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME",nombre)
                startActivity(intent)
            }
            else{
                text.setText("Hola Word")
            }

        }
    }
}