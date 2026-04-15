package com.example.mytodo.presentation.todolist

import androidx.compose.runtime.Immutable
import com.example.mytodo.domain.ToDoItem

@Immutable
sealed interface ToDoListState {
    data object Error: ToDoListState
    data object Loading: ToDoListState
    data class Content(
        val toDoItem: List<ToDoItem>,
        val newTaskText: String
    ): ToDoListState
}