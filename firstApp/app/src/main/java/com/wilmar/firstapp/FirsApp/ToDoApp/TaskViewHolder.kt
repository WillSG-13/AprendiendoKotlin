package com.wilmar.firstapp.FirsApp.ToDoApp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wilmar.firstapp.R

//esta clase es la que se inflara en cada vista
class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    //una ves llamados los componentes se le escribe encima
    fun render(task: Task) {
        //se le pone el texto tachado o no tachado dependiendo si esta seleccionado o no
        if (task.isSelected) {
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        //se le pone el estado al chek dependiendo de si la tarea esta seleccionada
        cbTask.isChecked = task.isSelected

        //se le setea el nombre de la tarea al view
        this.tvTask.text = task.name
        //siempre presente que se puede tomar datos de un when y guardarlo en una variable
        val color = when (task.category) {
            TaskCategory.Business -> R.color.toDoBusinessCategory
            TaskCategory.Other -> R.color.toDoOtherCategory
            TaskCategory.Personal -> R.color.toDoPersonalCategory
        }
        //se le extrae el contexto a las vistas
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(tvTask.context, color)
        )
    }

}