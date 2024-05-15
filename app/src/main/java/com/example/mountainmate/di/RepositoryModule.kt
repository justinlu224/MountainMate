package com.example.mountainmate.di

import com.example.mountainmate.data.datasource.LocalDataSource
import com.example.mountainmate.data.repository.ScheduleRepository
import com.example.mountainmate.data.room.AppDataBase
import com.example.mountainmate.data.room.CheckItemListDao
import com.example.mountainmate.data.room.DefaultItemDao
import com.example.mountainmate.data.room.ScheduleDao
import com.example.mountainmate.ui.itemlist.ItemListRepository
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
    fun bindItemListRepository(
        localDataSource: LocalDataSource
    ): ItemListRepository {
        return ItemListRepository(localDataSource)
    }

    @Provides
    fun provideDefaultItemDao(appDatabase: AppDataBase): DefaultItemDao {
        return appDatabase.defaultItemDao()
    }

    @Provides
    fun provideScheduleDao(appDatabase: AppDataBase): ScheduleDao {
        return appDatabase.scheduleDao()
    }

    @Provides
    fun provideCheckItemListDao(appDatabase: AppDataBase): CheckItemListDao {
        return appDatabase.checkItemDao()
    }

}