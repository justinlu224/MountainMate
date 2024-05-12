package com.example.mountainmate.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule")
    suspend fun getAllSchedules(): List<ScheduleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(scheduleEntity: ScheduleEntity)

    @Query("DELETE FROM schedule WHERE id = :id")
    suspend fun deleteSchedule(id: Int)

}