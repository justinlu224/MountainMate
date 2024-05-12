package com.example.mountainmate.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CheckItemListDao {

    @Query("SELECT * FROM check_item WHERE schedule_id = :scheduleId")
    suspend fun getCheckItemList(scheduleId: Int): List<CheckItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckItem(checkItemEntity: CheckItemEntity)

    @Update
    suspend fun updateCheckItem(checkItemEntity: CheckItemEntity)

    @Delete
    suspend fun deleteCheckItem(checkItemEntity: CheckItemEntity)
}