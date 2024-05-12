package com.example.mountainmate.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DefaultItemDao {
    @Query("SELECT * FROM default_item")
    suspend fun getAllDefaultItems(): List<DefaultItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDefaultItem(defaultItemEntity: DefaultItemEntity)

    @Update
    suspend fun updateDefaultItem(defaultItemEntity: DefaultItemEntity)

    @Delete
    suspend fun deleteDefaultItem(defaultItemEntity: DefaultItemEntity)
}