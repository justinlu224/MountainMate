package com.example.mountainmate.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mountainmate.di.CHECK_ITEM_TABLE_NAME

@Dao
interface CheckItemListDao {

    @Query("SELECT * FROM $CHECK_ITEM_TABLE_NAME WHERE schedule_id = :scheduleId ORDER BY category")
    suspend fun getCheckItemList(scheduleId: Int): List<CheckItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckItem(checkItemEntity: CheckItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckItems(checkItemEntity: List<CheckItemEntity>)

    @Update
    suspend fun updateCheckItem(checkItemEntity: CheckItemEntity)

    @Delete
    suspend fun deleteCheckItem(checkItemEntity: CheckItemEntity)
    @Query("UPDATE $CHECK_ITEM_TABLE_NAME SET is_checked = :isCheck WHERE id = :itemId")
    suspend fun updateCheckState(itemId: Int, isCheck: Boolean)
}