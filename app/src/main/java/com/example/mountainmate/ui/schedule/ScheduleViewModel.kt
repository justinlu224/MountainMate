package com.example.mountainmate.ui.schedule

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mountainmate.data.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScheduleState>(ScheduleState())
    val uiState = _uiState.asStateFlow()

    init {
        updateScheduleList()
    }

    private fun getDefaultItems() {
        viewModelScope.launch {
            val items = scheduleRepository.getDefaultItems().map { it.itemName }
            _uiState.update {
                it.copy(scheduleList = items)
            }
        }
    }

    fun onAction(action: ScheduleUiAction) {
        when(action) {
            is ScheduleUiAction.AddSchedule -> {
                addSchedule(action.name)
            }
            ScheduleUiAction.ClickItem -> {

            }
        }
    }

    private fun addSchedule(name: String){
        viewModelScope.launch {
            scheduleRepository.insertSchedule(name)
            updateScheduleList()
        }
    }

    private fun updateScheduleList() {
        viewModelScope.launch {
            val schedules = scheduleRepository.getAllSchedules().map { it.name }
            _uiState.update {
                it.copy(scheduleList = schedules)
            }
        }
    }
}