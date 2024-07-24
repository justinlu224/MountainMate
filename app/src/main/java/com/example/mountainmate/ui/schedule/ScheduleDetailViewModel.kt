package com.example.mountainmate.ui.schedule

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ScheduleDetailUiState(
    val showDialog: Boolean = false
)
@HiltViewModel
class ScheduleDetailViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleDetailUiState())
    val uiState = _uiState.asStateFlow()


    fun onAction(action: ScheduleDetailAction) {
        when (action) {
            is ScheduleDetailAction.ShowRecordDialog -> {
                updateDialogState(action.show)
            }

            is ScheduleDetailAction.SendRecord -> {
                // todo record entity
                updateDialogState(false)
            }
        }
    }

    private fun updateDialogState(show: Boolean) {
        _uiState.update {
            it.copy(showDialog = show)
        }
    }


}