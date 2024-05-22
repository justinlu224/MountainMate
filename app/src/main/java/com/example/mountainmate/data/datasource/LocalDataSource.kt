package com.example.mountainmate.data.datasource

import com.example.mountainmate.data.room.CheckItemEntity
import com.example.mountainmate.data.room.CheckItemListDao
import com.example.mountainmate.data.room.DefaultItemDao
import com.example.mountainmate.data.room.ScheduleDao
import com.example.mountainmate.data.room.ScheduleEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val defaultItemDao: DefaultItemDao,
    private val scheduleDao: ScheduleDao,
    private val checkItemListDao: CheckItemListDao
) {

    suspend fun getDefaultItems() = defaultItemDao.getAllDefaultItems()

    suspend fun insertSchedule(name: String): Long {
        return scheduleDao.insertSchedule(ScheduleEntity(name = name))
    }

    suspend fun getAllSchedules() = scheduleDao.getAllSchedules()

    suspend fun insertCheckItems(checkItems: List<CheckItemEntity>) {
        checkItemListDao.insertCheckItems(checkItems)
    }

    suspend fun insertCheckItem(checkItemEntity: CheckItemEntity) {
        checkItemListDao.insertCheckItem(checkItemEntity)
    }

    suspend fun getCheckItemList(scheduleId: Int) = checkItemListDao.getCheckItemList(scheduleId)
    suspend fun deleteCheckItem(checkItemEntity: CheckItemEntity) {
        checkItemListDao.deleteCheckItem(checkItemEntity)
    }

    suspend fun updateCheckState(itemId: Int, isCheck: Boolean) {
        checkItemListDao.updateCheckState(itemId, isCheck)
    }

    suspend fun deleteSchedule(id: Int) = scheduleDao.deleteSchedule(id)

}