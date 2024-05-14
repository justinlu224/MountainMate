package com.example.mountainmate.data.datasource

import com.example.mountainmate.data.room.DefaultItemDao
import com.example.mountainmate.data.room.ScheduleDao
import com.example.mountainmate.data.room.ScheduleEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val defaultItemDao: DefaultItemDao,
    private val scheduleDao: ScheduleDao
) {

    suspend fun getDefaultItems() = defaultItemDao.getAllDefaultItems()

    suspend fun insertSchedule(name: String) {
        scheduleDao.insertSchedule(ScheduleEntity(name = name))
    }

    suspend fun getAllSchedules() = scheduleDao.getAllSchedules()


}