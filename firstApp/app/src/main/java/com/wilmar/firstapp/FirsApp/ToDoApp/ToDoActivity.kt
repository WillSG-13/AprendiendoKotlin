package com.wilmar.firstapp.FirsApp.ToDoApp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RemoteViews.RemoteView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wilmar.firstapp.FirsApp.ToDoApp.TaskCategory.*
import com.wilmar.firstapp.R
import java.util.TimerTask

//Clase para la activity_to_do
class ToDoActivity : AppCompatActivity() {

    //lista con las categorias que se espera poner en pantalla
    private val categories = listOf(
        Business,
        Personal,
        Other
    )

    //listado para las tareas
    private val tasks = mutableListOf(
        Task("Prueba", Business),
        Task("Personal", Personal),
        Task("Otra", Other)
    )

    private lateinit var rvCategories: RecyclerView //componente para listas  de categorias
    private lateinit var categoriesAdapter: CategoriesAdapter//componente que adapta la lista en los componentes

    private lateinit var rvTask: RecyclerView//para la lista de tareas
    private lateinit var taskAdapter: TaskAdapter//componente que adapta la lista de la tareas

    private lateinit var fabAddTask: FloatingActionButton//para el boton de agragar tareas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)
        this.initComponent()
        this.initUI()
        this.initListener()
    }

    //inicia los componentes
    private fun initComponent() {
        this.rvCategories = findViewById(R.id.rvCategories)
        //se inicia el adapter y se le pasan las categorias
        this.categoriesAdapter =
            CategoriesAdapter(categories) { position -> updateCategory(position) }
        this.rvTask = findViewById(R.id.rvTasks) //se inicia el viewRecycler
        this.taskAdapter =
            TaskAdapter(tasks) { position -> onItemSelected(position) }//el adaptador para el recycler
        this.fabAddTask = findViewById(R.id.fabAddTask)//boton deagregar tarea
    }

    //inicia la vista
    private fun initUI() {
        //recycle view tiene dos partes una viewHolder  y adapter
        //se inicia el recyclerview  se le da un layoud
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //se le asigna el adapter al recycler
        rvCategories.adapter = categoriesAdapter


        //Se le pone una orientacion para vertical queda normal
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = taskAdapter
    }

    private fun initListener() {
        this.fabAddTask.setOnClickListener {
            this.showDialog()
        }
    }

    private fun updateCategory(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()
    }

    //esta funcion cambia el estado de seleccion y llama al update task
    private fun onItemSelected(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        this.updateTask()
    }

    //actualiza las vistas en el recycler view+
    //update task vuelve a cargar todos los componentes
    private fun updateTask() {
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        taskAdapter.tasks = newTasks
        //este es el metodo que los actualiza
        taskAdapter.notifyDataSetChanged()
    }

    private fun showDialog() {
        //se crea un dialogo
        val dialog = Dialog(this)
        //se asigna el xml al dialogo
        dialog.setContentView(R.layout.dialog_tasks)

        //para los elementos que estan dentro del dialogo
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        //se inician los componentes del dialogo
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        //se toma el radio button
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)
        //se toma el texto del nombre de la tarea

        //se le pone el listener al button
        btnAddTask.setOnClickListener {
            val taskName = etTask.text.toString()
            if (taskName.isNotEmpty()) {
                //una ves presionado el boton
                // se toma el dato del radioButton
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                //se revisa de que tipo es el radioBtn seleccionado  y se le asigna a la variable
                val currentCategory: TaskCategory =
                    when (selectedRadioButton.text) {
                        getString(R.string.textBussines) -> Business
                        getString(R.string.textPersonal) -> Personal
                        else -> Other
                    }//fin del when
                //se le agraga la tarea a la lista
                tasks.add(Task(taskName, currentCategory))
                //se actualiza la vista del recycler
                this.updateTask()
                //se cierra el dialogo
                dialog.hide()
            }//fin del listener
        }//fin del if
        dialog.show()
    }
}