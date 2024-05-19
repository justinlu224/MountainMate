package com.example.mountainmate.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.example.mountainmate.di.CHECK_ITEM_TABLE_NAME

@Entity(
    tableName = CHECK_ITEM_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ScheduleEntity::class,
            parentColumns = ["id"],
            childColumns = ["schedule_id"],
            onDelete = CASCADE
        )
    ]
)
data class CheckItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "schedule_id")
    val scheduleId: Int,
    @ColumnInfo(name = "item_name")
    val itemName: String,
    @ColumnInfo(name = "is_checked")
    val isChecked: Boolean = false,
    @ColumnInfo(name = "category")
    val category: Category
)

enum class Category {
    CAMPING,
    HIKING,
    CLIMBING,
    SKIING,
    OTHER
}
