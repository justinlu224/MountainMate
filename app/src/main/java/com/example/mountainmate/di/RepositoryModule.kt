package com.example.mountainmate.di

import com.example.mountainmate.data.datasource.LocalDataSource
import com.example.mountainmate.data.repository.ScheduleRepository
import com.example.mountainmate.data.room.AppDataBase
import com.example.mountainmate.data.room.DefaultItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun bindScheduleRepository(
        localDataSource: LocalDataSource
    ): ScheduleRepository {
        return ScheduleRepository(localDataSource)
    }

    @Provides
    @Singleton
    fun provideDefaultItemDao(appDatabase: AppDataBase): DefaultItemDao {
        return appDatabase.defaultItemDao()
    }

}