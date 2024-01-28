package com.wilmar.firstapp.FirsApp.SuperHeroApp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wilmar.firstapp.databinding.ItemSuperHeroBinding

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperHeroBinding.bind(view) // binding es una instancia del view que esta en el xml no de todo el xml

    //metodo que carga la data en el componente
    fun bind(superHeroItemResponse: SuperHeroItemResponse, onItemSelected: (String) -> Unit) {
        binding.superHeroName.text =
            superHeroItemResponse.name // le pone el nombre del super heroe al componente especifico
        Picasso.get().load(superHeroItemResponse.image.url).into(binding.ivSuperHero)
        binding.root.setOnClickListener {
            onItemSelected(superHeroItemResponse.id)
        }
    }
}