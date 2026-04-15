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
    tasks: List<ToDoItem>,
    newTaskText: String,
    onTextChange: (String) -> Unit,
    onToggleTask: (Int, Boolean) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 12.dp)){
        OutlinedTextField(
            value = newTaskText,
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Новая задача")},
            placeholder = {Text("Введите дело")},
            singleLine = true

        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = onAddClick,
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
            items(tasks){ task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Checkbox(
                        checked = task.isDone,
                        onCheckedChange = { checked ->
                            onToggleTask(task.id, checked)}
                    )
                    Text(text = task.text)
                }

            }
        }
    }

}

