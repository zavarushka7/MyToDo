package com.example.mytodo.presentation.todolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytodo.domain.ToDoItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun ToDoListContent(
    // Такой подход делает composable независимым от ViewModel: он не знает, откуда приходят данные, а только отображает их и отправляет события наружу. это и есть state hoisting
    tasks: List<ToDoItem>, // список задач, который надо показать
    newTaskText: String, // текущий текст в поле ввода
    onTextChange: (String) -> Unit, // что делать, когда пользователь печатает
    onToggleTask: (Int, Boolean) -> Unit, // что делать, когда пользователь нажимает чекбокс
    onAddClick: () -> Unit, // что делать, когда пользователь нажимает кнопку добавить
    modifier: Modifier = Modifier // внешний способ управлять размером, padding  и положением composable
) {
    // В документации Compose рекомендуют принимать modifier параметром и применять его к корневому элементу composable
    Column(modifier = modifier
        .fillMaxSize() // занять все доступное пространство родителя
        .padding(horizontal = 16.dp, vertical = 12.dp)) // внутренние отступы, чтобы контент не прилипал к краям
    {
        OutlinedTextField(
            value = newTaskText, // текст в поле берется из состояния, которое пришло снаружи
            onValueChange = onTextChange, // при каждом вводе пользователем функция сообщает наверх новое значение
            modifier = Modifier.fillMaxWidth(), // поле растягивается по ширине
            label = {Text("Новая задача")}, // подпись поля
            placeholder = {Text("Введите дело")}, // подсказка, когда поле пустое
            singleLine = true // ввод в одну строку

        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = onAddClick, // при нажатии вызывает onAddClick
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Добавить")
        }
        Spacer(Modifier.height(16.dp))
        Column(){
            val currentDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
            val formattedDate = currentDate.format(formatter)
            Text(text = formattedDate)

            Spacer(Modifier.height(24.dp))

            Text(text = "Список дел на сегодня:")
        }
        Spacer(Modifier.height(32.dp))
        LazyColumn(){
            items(tasks){ task -> // для каждого элемента из спика tasks создай одну строку UI
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically // выравнивает чекбокс и текст по одной горизонтальной линии
                ){
                    Checkbox(
                        checked = task.isDone, // текущее состояние задачи
                        onCheckedChange = { checked ->
                            onToggleTask(task.id, checked)}
                    )
                    Text(text = task.text)
                }

            }
        }
    }

}

