package com.example.mytodo.data

import com.example.mytodo.domain.ToDoItem
import com.example.mytodo.domain.ToDoListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoListRepositoryImpl @Inject constructor(

) : ToDoListRepository{
    override suspend fun getToDoList(): List<ToDoItem> {
        return listOf(
            ToDoItem(1, "сделать", false),
            ToDoItem(2, "сд nfjoenf елать", true),
        )
    }
}