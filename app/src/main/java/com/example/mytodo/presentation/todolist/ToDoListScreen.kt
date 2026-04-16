package com.example.mytodo.presentation.todolist


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun ToDoListScreen() {
    val viewModel = hiltViewModel<ToDoListViewModel>()
    // проще говоря - viewModel.state меняется, Compose это замечает, ToDoListScreen() перерисовываетс, нужная ветка when обновляется
    val state by viewModel.state.collectAsStateWithLifecycle() // беру StateFlow из ViewModel и превращаю его в Compose State, чтобы UI автоматически перерисовывался при изменениях. collectAsStateWithLifecycle() делает это с учетом жц, то есть собирает flow безопаснее, чем обычный collectAsState(), когда экран реально активен

    Scaffold() { // контейнер Material3 для построения экрана.
        contentPadding -> // отступы, которые Scaffold сам рассчитает для твоего контента
        when (val currentState = state){
            is ToDoListState.Loading -> {
                ToDoListLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding() // добавить безопасные отступы, чтобы контент не залезал под системные области
                        .padding(contentPadding) // учесть внутренние отступы от Scaffold
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


