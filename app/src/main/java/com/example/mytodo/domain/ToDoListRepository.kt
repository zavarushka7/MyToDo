package com.example.mytodo.domain

interface ToDoListRepository {
    suspend fun getToDoList(): List<ToDoItem>
}