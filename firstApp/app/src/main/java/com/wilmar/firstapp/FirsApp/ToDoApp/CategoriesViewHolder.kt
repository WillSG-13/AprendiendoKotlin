package com.wilmar.firstapp.FirsApp.ToDoApp

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.wilmar.firstapp.FirsApp.MenuActivity
import com.wilmar.firstapp.R

//este es el objeto que especifica como va a ser cada uno de los componentes que estaran en pantalla
class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //se inician todas las vistas del componente  del xml
    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val card: CardView = view.findViewById(R.id.card)

    fun render(taskCategory: TaskCategory, onItemselected: (Int) -> Unit) {

        this.setBackgrounColor(taskCategory)


        itemView.setOnClickListener {
            onItemselected(layoutPosition)
        }

        when (taskCategory) {
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundResource(R.color.toDoBusinessCategory)
            }

            TaskCategory.Other -> {
                tvCategoryName.text = "Otro"
                divider.setBackgroundResource(R.color.toDoOtherCategory)
            }

            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundResource(R.color.toDoPersonalCategory)
            }
        }
    }
    private fun setBackgrounColor(taskCategory: TaskCategory){
        val color = if (taskCategory.isSelected)
            R.color.toDoBackgroundCard
        else R.color.toDoBackgroundDisable
        card.setCardBackgroundColor(ContextCompat.getColor(card.context,color))
    }
}