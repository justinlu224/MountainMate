package com.example.mountainmate.data.repository

import com.example.mountainmate.data.datasource.LocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
) {

    suspend fun getDefaultItems() = localDataSource.getDefaultItems()

    suspend fun insertSchedule(name: String) {
        localDataSource.insertSchedule(name)
    }

    suspend fun getAllSchedules() = localDataSource.getAllSchedules()

}