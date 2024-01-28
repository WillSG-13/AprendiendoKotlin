package com.wilmar.firstapp.FirsApp.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.wilmar.firstapp.R

class ResoultIMCActivity : AppCompatActivity() {

    private lateinit var tvIMC: TextView

    private lateinit var tvResult: TextView
    private lateinit var tvImc: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalcular: Button

    private var imc: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resoult_imcactivity)
        //Metodos de inicializacion
        this.initComponent()
        this.initUI(imc)
        this.initListeners()
    }
    // se inicializan los componentes del XML
    private fun initComponent() {
        this.tvResult = findViewById(R.id.tvResult)
        this.tvImc = findViewById(R.id.tvImc)
        this.tvDescription = findViewById(R.id.tvDescription)

        this.imc = intent.extras?.getDouble(ImcActivity.IMC_KEY) ?: -1.0
        this.btnRecalcular = findViewById(R.id.btnRecalculate)
    }

    private fun initListeners() {
        this.btnRecalcular.setOnClickListener {
            val intent = Intent(this,ImcActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initUI(result: Double) {
        tvImc.text = result.toString()
        when (result) {
            //bajo peso
            in 0.00..18.50 -> {
                tvResult.text = getString(R.string.tituloBajoPeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.pesoBajo))
                tvDescription.text = getString(R.string.descripcionBajoPeso)
            }
            //normal
            in 18.51..24.99 -> {
                tvResult.text = getString(R.string.tituloNormalPeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.pesoNormal))
                tvDescription.text = getString(R.string.descripcionNormalPeso)
            }
            //sobre peso
            in 25.00..29.99 -> {
                tvResult.text = getString(R.string.tituloSobrePeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.pesoSobrePeso))
                tvDescription.text = getString(R.string.tituloSobrePeso)
            }
            //obesidad
            in 30.00..99.00 -> {
                tvResult.text = getString(R.string.tituloSobrePeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescription.text = getString(R.string.descripcionSobrePeso)
            }

            else -> { //error
                tvImc.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

}