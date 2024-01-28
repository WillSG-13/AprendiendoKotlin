package com.wilmar.firstapp.FirsApp.ToDoApp

data class Task(val name: String, val category: TaskCategory, var isSelected: Boolean = false) {
}