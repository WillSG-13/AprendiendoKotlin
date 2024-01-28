package com.wilmar.firstapp.FirsApp.SuperHeroApp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.wilmar.firstapp.FirsApp.SuperHeroApp.DetailSuperHeroActivity.Companion.EXTRA_ID
import com.wilmar.firstapp.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//calse principal para manejar llamadas a la Api
class SuperHeroListActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySuperHeroListBinding//para sincronizar con los componentes de la XML
    private lateinit var retrofit: Retrofit//objeto para la api
    private lateinit var adapter: superHeroAdapter//adaptador para el rv


    //se inicia el retrofit que comunicara con la api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)//se carga toda la vista
        setContentView(binding.root)
        this.initComponents()//inicia los componentes
        this.initUi()//inicia la interfaz
    }

    //metodo para iniciar el UI
    private fun initUi() {
        //se accede a la vista searchView y se le asigna el Listener atraves del setOnQueryTextListener te metodo en este caso
        //resibe un objeto anonimo que implementa la interfaz SearchView.OnQueryTextListener  esta obliga a implementar los metodos onQueryTextSubmit,onQueryTextChange
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { //se activa cuando se envia una consulta atravez del searcView
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) =
                false//se activa cada vez que se cambia el texto
        })
        this.adapter = superHeroAdapter(){navigateToDetail(it)}//se inicializa el adaptador
        binding.rvSuperHero.setHasFixedSize(true) //esto le indica al rv que todos los componentes mantendran siempre el mismo tamano, esto mejora el rendimiento
        binding.rvSuperHero.layoutManager =
            LinearLayoutManager(this)//cuando se crea con un parametro se unicializa con una orientacion vertical
        binding.rvSuperHero.adapter = adapter//se asigna un adaptador
    }

    //inicializa los componentes importantes
    private fun initComponents() {
        this.retrofit =
            this.getRetrofit()//asigna una api a una variavle llamada retrofit para hacer las consultas atravez de ella
    }

    //Metodo para buscar atraves del nombre
    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true // se pone la animacion de busqueda

        CoroutineScope(Dispatchers.IO).launch {//se inicia una corutina
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java)
                    .getSuperHeroes(query.trim())//se obtiene la respuesta de la api se almacena en myresponse
            if (myResponse.isSuccessful) {
                val response: SuperHeroDataResponse? =
                    myResponse.body()//se almacena la informacion del cuerpo donde esta lo que interesa
                if (response != null ) { // se consulta si tiene cuerpo
                    runOnUiThread {//se inicia un hilo aparte para realizar las actualizaciones a la interfaz
                        adapter.uptadeList(response.superHeroes)//se le pasa la lista al metodo update
                        binding.progressBar.isVisible = false//se quita la animacion de busqueda
                    }//fin del if response!=null
                }
                else{
                    binding.progressBar.isVisible = false
                }
            }//fin del if isSuccess
        }//fin de la subrutina
    }//fin del metodo search

    //se inicializa una referencia de retrofit con su respectiva configiracion
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/2386528841539797/")//se le asigna una baese
            .addConverterFactory(GsonConverterFactory.create())//se le asigna un covertidor
            .build()//se construye
    }
    private fun navigateToDetail(id:String){
        val intent= Intent(this,DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)
    }
}