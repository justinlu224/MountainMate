package com.example.mountainmate.ui.schedule

sealed class ScheduleUiAction {
    data class AddSchedule(val name: String) : ScheduleUiAction()

    data object ClickItem : ScheduleUiAction()
}