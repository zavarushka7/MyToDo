package com.example.mytodo.domain

data class ToDoItem(
    val id: Int,
    val text: String,
    val isDone: Boolean
)