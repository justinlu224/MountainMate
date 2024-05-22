package com.example.mountainmate.ui.schedule

sealed class ScheduleUiAction {
    data class AddSchedule(val name: String) : ScheduleUiAction()

    data class OpenDeleteDialog(val id: Int) : ScheduleUiAction()

    data object DismissDeleteDialog : ScheduleUiAction()

    data object DeleteSchedule: ScheduleUiAction()
}