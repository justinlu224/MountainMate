package com.example.mountainmate.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(TypeConverts::class)
@Database(entities = [DefaultItemEntity::class, ScheduleEntity::class, CheckItemEntity::class, ScheduleLogEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun defaultItemDao(): DefaultItemDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun checkItemDao(): CheckItemListDao
}