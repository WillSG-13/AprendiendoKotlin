package com.wilmar.firstapp.FirsApp.SuperHeroApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.squareup.picasso.Picasso
import com.wilmar.firstapp.databinding.ActivityDetailSuperHeroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var retrofit: Retrofit
    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()//se saca el id de la extra
        this.initComponents()
        this.getSuperHeroInfomation(id)


    }

    private fun getSuperHeroInfomation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHero: Response<SuperHeroData> =
                retrofit.create(ApiService::class.java).getSuperHeroID(id)
            if (superHero.body() != null) {
                runOnUiThread {
                    createUI(superHero.body()!!)
                }
            }
        }
    }

    private fun createUI(superHero: SuperHeroData) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text=superHero.name
        prepareStats(superHero.powerStats)
    }

    private fun prepareStats(powerStats: PowerStats) {
        updateHeight(binding.viewCombat,powerStats.combat.toFloat())
        updateHeight(binding.viewDurability,powerStats.durability.toFloat())
        updateHeight(binding.viewPower,powerStats.power.toFloat())
        updateHeight(binding.viewIntelligence,powerStats.intelligence.toFloat())
        updateHeight(binding.viewSpeed,powerStats.speed.toFloat())
        updateHeight(binding.viewStrength,powerStats.strength.toFloat())
    }
    private fun updateHeight(view:View,stat:Float){
        val params = view.layoutParams // se extraen los parametros de la viewCombat
        params.height =PxtoDP(stat)
        view.layoutParams=params
    }
    //combierte los px en dp
    private fun PxtoDP(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,resources.displayMetrics).roundToInt()
    }
    private fun initComponents() {
        this.retrofit = getRetrofit()
    }

    //se crea un retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}