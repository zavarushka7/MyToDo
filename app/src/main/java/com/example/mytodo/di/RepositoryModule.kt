package com.example.mytodo.di

import com.example.mytodo.data.ToDoListRepositoryImpl
import com.example.mytodo.domain.ToDoListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//  Этот файл Hilt-модуль, который связывает интерфейс ToDoListRepository и его реализацию ToDoListRepositoryImpl в единый singleton-обьект
// В архитектуре DI он выполняет роль "правила связи": когда кто-то прости ToDoListRepository, Hilt вместо этого реально создает и дает ToDoListRepositoryImpl
@Module // Это модуль Dagger/Hilt блок, где ты описываешь правила связывания зависимостей (что из чего как создается или привязывается)
@InstallIn(SingletonComponent::class) // говорит Hilt, что модуль относится к singleton-уровню приложения: зависимости из этого модуля будут доступны глобально и живут всю жизнь приложения
abstract class RepositoryModule { // abstract - модуль не нужно, чтобы его инстанцировали (создавали экземпляр класса), важны только его абстрактные методы. Hilt/Dagger генерируют реализацию этиъ методов автоматически
    @Binds // это способ сказать интерфейс -> реализация. то есть интерфейс ToDoListRepository привязан к классу ToDoListRepositoryImpl.
    @Singleton // означает, что результат привязки ToDoListRepository будет одним и тем же объектом нв все приложение
    abstract fun bindToDoListRepository(
        impl: ToDoListRepositoryImpl
    ): ToDoListRepository
}