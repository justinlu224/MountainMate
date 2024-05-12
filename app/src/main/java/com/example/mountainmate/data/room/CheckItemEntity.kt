package com.example.mountainmate.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "check_item",
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
    val isChecked: Boolean,
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
