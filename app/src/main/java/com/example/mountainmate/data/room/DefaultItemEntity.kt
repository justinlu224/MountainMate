package com.example.mountainmate.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mountainmate.di.DEFAULT_ITEM_TABLE_NAME

@Entity(tableName = DEFAULT_ITEM_TABLE_NAME)
data class DefaultItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "item_name")
    val itemName: String,
    @ColumnInfo(name = "category")
    val category: Category
)
