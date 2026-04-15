package com.example.mytodo.presentation.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.domain.ToDoItem
import com.example.mytodo.domain.ToDoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val repository: ToDoListRepository
) : ViewModel() {
    private val _state = MutableStateFlow<ToDoListState>(ToDoListState.Loading)
    val state = _state.asStateFlow()

    init{
        loadToDoList()
    }

    // способ начать загрузку списка дел из Repository и обновить состояние экрана
    fun loadToDoList(){
        viewModelScope.launch { // запускаем корутину, которая привязана к жц ViewModel. Если ViewModel перестает существовать, корутина автоматически отменяется, и ты не получишь "забытых" фоновых задач
            _state.value = ToDoListState.Loading

            runCatching { // безопасный способ вызвать потенциально падающую suspend-функцию
                repository.getToDoList()
            }.onSuccess { item-> // если getToDoList выполнится успешно, приходит список задач item
                _state.value = ToDoListState.Content(
                    toDoItem = item,
                    newTaskText = ""
                )
            }.onFailure {
                _state.value = ToDoListState.Error
            }
        }
    }

    fun addTask(){
        val currentState = _state.value
        if (currentState is ToDoListState.Content){
            var newTask = currentState.newTaskText.trim()
            if (newTask.isBlank()) return

            val newId = (currentState.toDoItem.maxOfOrNull { it.id } ?: 0) + 1
            val task = ToDoItem(newId, newTask, false)
            _state.value = currentState.copy(
                toDoItem = currentState.toDoItem + task,
                newTaskText = ""
            )

        }

    }
    fun textChange(text: String) {
        val currentState = _state.value
        if (currentState is ToDoListState.Content){
            _state.value = currentState.copy(newTaskText = text)
        }
    }
    fun toggleTask(taskId: Int, checked: Boolean){
        val currentState = _state.value
        if (currentState is ToDoListState.Content){
            val updatedList = currentState.toDoItem.map { task ->
                if (task.id == taskId) {
                    task.copy(isDone = checked)
                } else {
                    task
                }
            }
            _state.value = currentState.copy(toDoItem = updatedList)
        }

    }
}