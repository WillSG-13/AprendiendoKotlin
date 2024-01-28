package com.wilmar.firstapp.FirsApp.ToDoApp

//clase sellada  esta clase sera la lista que se presentara en pantalla
sealed class TaskCategory (var isSelected:Boolean = true){
    object Personal: TaskCategory()
    object Business :TaskCategory()
    object Other : TaskCategory()
}