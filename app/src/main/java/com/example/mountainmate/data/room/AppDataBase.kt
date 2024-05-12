package com.example.mountainmate.data.room

import androidx.room.Database
import androidx.room.TypeConverters

@Database(entities = [DefaultItemEntity::class, ScheduleEntity::class, CheckItemEntity::class], version = 1)
@TypeConverters(TypeConverts::class)
abstract class AppDataBase {
    abstract fun defaultItemDao(): DefaultItemDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun checkItemDao(): CheckItemListDao
}