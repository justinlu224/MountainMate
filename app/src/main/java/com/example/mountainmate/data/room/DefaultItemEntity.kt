package com.example.mountainmate.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "default_item")
data class DefaultItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "item_name")
    val itemName: String,
    @ColumnInfo(name = "category")
    val category: Category
)
