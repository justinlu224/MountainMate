package com.example.mountainmate.data.repository

import com.example.mountainmate.data.datasource.LocalDataSource
import com.example.mountainmate.data.room.convertToCheckItemEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
) {

    suspend fun getDefaultItems() = localDataSource.getDefaultItems()

    suspend fun insertSchedule(name: String) {
        val id = localDataSource.insertSchedule(name).toInt()
        val defaultItems = localDataSource.getDefaultItems()
        localDataSource.insertCheckItems(defaultItems.map { it.convertToCheckItemEntity(id) })
    }

    suspend fun getAllSchedules() = localDataSource.getAllSchedules()
    suspend fun deleteSchedule(id: Int) = localDataSource.deleteSchedule(id)
}