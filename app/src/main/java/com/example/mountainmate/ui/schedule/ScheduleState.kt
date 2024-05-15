package com.example.mountainmate.ui.schedule

import com.example.mountainmate.data.room.ScheduleEntity

data class ScheduleState(
    val scheduleList: List<ScheduleEntity> = mutableListOf(),
)
