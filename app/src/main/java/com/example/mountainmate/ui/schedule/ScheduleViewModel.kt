package com.example.mountainmate.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mountainmate.data.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private var deleteScheduleId: Int? = null

    fun onAction(action: ScheduleUiAction) {
        when(action) {
            is ScheduleUiAction.AddSchedule -> {
                addSchedule(action.name)
            }
            is ScheduleUiAction.OpenDeleteDialog -> {
                _uiState.update {
                    it.copy(openDeleteDialog = true)
                }
                deleteScheduleId = action.id
            }

            is ScheduleUiAction.DeleteSchedule -> {
                _uiState.update {
                    it.copy(openDeleteDialog = false)
                }
                deleteScheduleId?.let {
                    deleteSchedule(it)
                }
            }

            ScheduleUiAction.DismissDeleteDialog -> {
                _uiState.update {
                    it.copy(openDeleteDialog = false)
                }
                deleteScheduleId = null
            }
        }
    }

    private fun deleteSchedule(id: Int) {
        viewModelScope.launch {
            scheduleRepository.deleteSchedule(id)
            updateScheduleList()
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
            val schedules = scheduleRepository.getAllSchedules()
            _uiState.update {
                it.copy(scheduleList = schedules)
            }
        }
    }
}