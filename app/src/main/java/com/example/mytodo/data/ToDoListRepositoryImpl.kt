package com.example.mytodo.data

import com.example.mytodo.domain.ToDoItem
import com.example.mytodo.domain.ToDoListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // означает, что Hilt будет использовать один общий экземпляр этого репозитория в пределах singleton-component приложения, если binding тоже обьявлен как singleton
// Репозиторий относится к data layer: он отдает дальные остальной части приложения и скрывает источник этих данных, чтобы ViewModel гн знала, приходят ли оно из сети, базы данных или mock
class ToDoListRepositoryImpl @Inject constructor( // @Inject constructor - этот класс можно создавать автоматически через di

) : ToDoListRepository{
    override suspend fun getToDoList(): List<ToDoItem> { // suspend - означает, что эта функция рассчитана на вызов из корутины или другой suspend-функции, то есть подходит для потенциально долгих операций вроде базы данных или сети, не блокируя поток напрямую
        return listOf(
            ToDoItem(1, "сделать", false),
            ToDoItem(2, "сд nfjoenf елать", true),
        )
    }
}