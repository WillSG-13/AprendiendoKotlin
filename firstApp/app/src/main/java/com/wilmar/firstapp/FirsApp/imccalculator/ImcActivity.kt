package com.wilmar.firstapp.FirsApp.imccalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat
import com.wilmar.firstapp.R

class ImcActivity : AppCompatActivity() {

    //para saber que sexo fue presionado
    private var isMaleSelected: Boolean = false
    private var isFemaleSelected: Boolean = true
    private var currentWeight: Int = 60//int para el peso
    private var currentHeigth: Int = 120 //int para la altura
    private var currentAge: Int = 20 //int para la edad

    //lateinit es para que se inicialice despues
    private lateinit var cardMale: CardView //instancia del view cardMale
    private lateinit var cardFemale: CardView //instancia del view cardFemale

    //componentes para la edad
    private lateinit var tvHeight: TextView //intancia para un textView
    private lateinit var rsHeight: RangeSlider//instancia de un rangeSlider

    //botones para bajar y subir el peso
    private lateinit var btnSubstractWeght: FloatingActionButton // instancia de un botonFlotante
    private lateinit var btnPlusWeight: FloatingActionButton //intancia de un botonFlotante
    private lateinit var tvWeight: TextView //instancia de un TextView

    //botones para bajar y subir la edad
    private lateinit var btnSubstractAge: FloatingActionButton //instancia de un boton flotante
    private lateinit var btnPlusAge: FloatingActionButton //instancia de un botonflotante
    private lateinit var tvAge: TextView //instancia del textView de la edad

    //para calcular el inidice de masa corporal
    private lateinit var btnCalculate: Button //instancia del boton para calcular

    //el companioon contiene todos los coponentes staticos de una clase
    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    //metodo que se ejecuta para crear el objeto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)//esto asocia la vista XML  al back
        this.initComponent()//metodo para iniciar los componentes
        this.initLister()//le pone los listener a los componentes
        this.initUi()//metodo que inicia la interfaz
    }

    //metodo que inicializa los componentes de la interfaz
    private fun initComponent() {
        this.cardMale = findViewById(R.id.card1)
        this.cardFemale = findViewById(R.id.card2)
        this.tvHeight = findViewById(R.id.tvHeight)
        this.rsHeight = findViewById(R.id.rsHeight)
        this.btnPlusWeight = findViewById(R.id.btnPlusWeight)
        this.btnSubstractWeght = findViewById(R.id.btnSubtractWeight)
        this.tvWeight = findViewById(R.id.tvWeight)
        this.btnPlusAge = findViewById(R.id.btnPlusAge)
        this.btnSubstractAge = findViewById(R.id.btnSubstractAge)
        this.tvAge = findViewById(R.id.tvAge)
        this.btnCalculate = findViewById(R.id.btnCalculate)
    }

    //metodo que asigna los listener a los componentes correspondientes
    private fun initLister() {
        this.cardMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        this.cardFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }




        this.rsHeight.addOnChangeListener { _, altura, _ ->
            val df = DecimalFormat("#.##")
            this.currentHeigth = df.format(altura).toInt()
            tvHeight.text = currentWeight.toString()
        }
        this.btnPlusWeight.setOnClickListener {
            this.currentWeight++
            this.tvWeight()
        }
        this.btnSubstractWeght.setOnClickListener {
            this.currentWeight--
            this.tvWeight()
        }
        this.btnPlusAge.setOnClickListener {
            this.currentAge++
            this.tvAge()
        }
        this.btnSubstractAge.setOnClickListener {
            this.currentAge--
            this.tvAge()
        }
        this.btnCalculate.setOnClickListener {
            this.calculateImc()
        }
    }

    //metodo que calcula el indice de masa corporal
    private fun calculateImc() {
        val format = DecimalFormat("#.##")//se crea un formateador de cifras a dos decimales
        //se asigna el imc formateado
        val imc = format.format(currentWeight / (Math.pow(currentHeigth.toDouble() / 100, 2.0)))
            .toDouble()
        val imcScreen =
            Intent(this, ResoultIMCActivity::class.java)//se crea el intent de la pantalla
        imcScreen.putExtra(IMC_KEY, imc)//se le pone un extra
        startActivity(imcScreen)//se lanza
    }

    //metodo inicializa los detalleas de la pantalla
    private fun initUi() {
        this.setGenderColor()
        this.tvWeight()
        this.tvAge()
    }

    //se toma el retorno del metodo getBackgroundColor y ponen el color en el fondo
    private fun setGenderColor() {
        this.cardMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        this.cardFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    //metodo que retorna  el color al sexo dependiendo de la variable del estado
    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReferece =
            if (isSelectedComponent)
                R.color.background_component_selected
            else
                R.color.background_component
        return ContextCompat.getColor(this, colorReferece)
    }

    //metodo pone el peso al textView Age
    private fun tvWeight() {
        this.tvWeight.text = currentWeight.toString()
    }

    //metodo pone la edad al textview de edad
    private fun tvAge() {
        this.tvAge.text = currentAge.toString()
    }

    //Metodo cambia el estado del color del componente
    private fun changeGender() {
        this.isFemaleSelected = !isFemaleSelected
        this.isMaleSelected = !isMaleSelected
    }
}