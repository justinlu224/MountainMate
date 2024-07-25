package com.example.mountainmate.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ScheduleDetailUiState(
    val showDialog: Boolean = false
)
@HiltViewModel
class ScheduleDetailViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleDetailUiState())
    val uiState = _uiState.asStateFlow()


    abstract class BaseEvent

    private val eventChannel = Channel<BaseEvent>()
    val eventsFlow = eventChannel.receiveAsFlow()

    private var accessLocalPermission = false

    sealed class Event : BaseEvent() {
        data object CheckUserLocationPermission : Event()
    }


    fun onAction(action: ScheduleDetailAction) {
        when (action) {
            is ScheduleDetailAction.ShowRecordDialog -> {
                updateDialogState(action.show)
            }

            is ScheduleDetailAction.SendRecord -> {
                // todo record entity
                updateDialogState(false)
            }

            is ScheduleDetailAction.AccessLocalPermission -> {
                accessLocalPermission = true
            }

            ScheduleDetailAction.CheckUserLocationPermission -> {
                sendEvent(Event.CheckUserLocationPermission)
            }
        }
    }

    private fun updateDialogState(show: Boolean) {
        _uiState.update {
            it.copy(showDialog = show)
        }
    }

    private fun sendEvent(event: BaseEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }


}