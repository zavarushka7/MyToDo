package com.example.mytodo.presentation.todolist


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun ToDoListScreen() {
    val viewModel = hiltViewModel<ToDoListViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold() {
        contentPadding ->
        when (val currentState = state){
            is ToDoListState.Loading -> {
                ToDoListLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .padding(contentPadding)
                )
            }
            is ToDoListState.Error -> {
                ToDoListError(
                    onRefreshClick = { viewModel.loadToDoList()},
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .padding(contentPadding)
                )

            }
            is ToDoListState.Content -> ToDoListContent(
                tasks = currentState.toDoItem,
                onToggleTask = { taskId, checked ->
                    viewModel.toggleTask(taskId, checked)
                },
                newTaskText = currentState.newTaskText,
                onTextChange = {task ->
                    viewModel.textChange(task)
                },
                onAddClick = {
                    viewModel.addTask()
                }
            )
        }
    }
}



@Preview
@Composable
private fun Preview(){
    ToDoListScreen()
}