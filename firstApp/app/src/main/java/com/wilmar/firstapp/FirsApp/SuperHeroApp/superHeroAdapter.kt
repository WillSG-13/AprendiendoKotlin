package com.wilmar.firstapp.FirsApp.SuperHeroApp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wilmar.firstapp.R

class superHeroAdapter(var superHeroList: List<SuperHeroItemResponse> = emptyList(),private val onItemSelected:(String)-> Unit) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    // toma la lista  con los datos y los envia a refrescar atravez del metodo notifyC...
    fun uptadeList(superHeroList: List<SuperHeroItemResponse>) {
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    //cuando se lanza el notify este ejecuta estos dos metodos
    //se infla la vista y se retorna para ser usada en el OnBind
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_super_hero, parent, false))

    }
    //on bind toma la viewHolder
    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(superHeroList[position],onItemSelected)
    }

    override fun getItemCount() = superHeroList.size
}