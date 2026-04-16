package com.example.mytodo.presentation.todolist

import androidx.compose.runtime.Immutable
import com.example.mytodo.domain.ToDoItem

@Immutable // аннотация, которая говорит, что все реализации ToDoListState должны быть неизменяемыми (data class/ object), чтобы их нельзя было случайно изменить
sealed interface ToDoListState { // sealed interface - интерфейс, который может иметь только заранее известные реализации в этом же файле или модуле
    data object Error: ToDoListState // object - это один единственный экземпляр. data object - это object с data-семантика (как data class но без полей)
    data object Loading: ToDoListState
    data class Content(
        val toDoItem: List<ToDoItem>,
        val newTaskText: String
    ): ToDoListState
}