package com.example.mountainmate.data.repository

import android.location.Location
import com.example.mountainmate.data.datasource.LocalDataSource
import com.example.mountainmate.data.room.ScheduleLogEntity
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

    suspend fun insertScheduleLog(scheduleId: Int, description: String, time: Long, location: Location?): Long {
        val scheduleLogEntity = ScheduleLogEntity(
            scheduleId = scheduleId,
            time = time,
            description = description,
            longitude = location?.longitude,
            latitude = location?.latitude
        )
        return localDataSource.insertScheduleLog(scheduleLogEntity)
    }

    suspend fun getScheduleLogs(scheduleId: Int): List<ScheduleLogEntity> {
        return localDataSource.getScheduleLogs(scheduleId)
    }
}