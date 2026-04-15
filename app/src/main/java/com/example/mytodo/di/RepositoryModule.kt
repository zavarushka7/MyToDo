package com.example.mytodo.di

import com.example.mytodo.data.ToDoListRepositoryImpl
import com.example.mytodo.domain.ToDoListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindToDoListRepository(
        impl: ToDoListRepositoryImpl
    ): ToDoListRepository
}