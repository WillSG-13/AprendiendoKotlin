package com.wilmar.firstapp.FirsApp.ToDoApp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wilmar.firstapp.R

class TaskAdapter( var tasks: List<Task>, private val ontaskSelected: (Int) -> Unit) :
    RecyclerView.Adapter<TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.item_todo_task, parent, false)

        return TaskViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasks[position])
        // se le pone el listener al item view que cuando se se toque se llame a la funcion no taskSelected
        holder.itemView.setOnClickListener{
            ontaskSelected(position)
        }
    }
}