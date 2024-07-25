package com.example.mountainmate.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mountainmate.data.repository.ScheduleRepository
import com.example.mountainmate.data.room.ScheduleLogEntity
import com.example.mountainmate.util.LocationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ScheduleDetailUiState(
    val scheduleLogs: List<ScheduleLogEntity> = listOf(),
    val showDialog: Boolean = false
)
@HiltViewModel
class ScheduleDetailViewModel @Inject constructor(
    private val locationHelper: LocationHelper,
    private val scheduleRepository: ScheduleRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleDetailUiState())
    val uiState = _uiState.asStateFlow()
    var scheduleId:Int? = null


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
                insertScheduleLog(action.text)
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

    private fun insertScheduleLog(text: String) {
        viewModelScope.launch {
            val location = locationHelper.getCurrentLocation()
            val time = System.currentTimeMillis()
            scheduleId?.let {
                val resultDeferred = async { scheduleRepository.insertScheduleLog(it, text, time, location) }
                resultDeferred.await().run {
                    getScheduleLogs(it)
                }
            }
        }
    }

    fun getScheduleLogs(scheduleId: Int) {
        viewModelScope.launch {
            val scheduleLogsDeferred = async { scheduleRepository.getScheduleLogs(scheduleId) }
            updateScheduleLogs(scheduleLogsDeferred.await())
        }
    }

    private fun updateScheduleLogs(scheduleLogs: List<ScheduleLogEntity>) {
        _uiState.update {
            it.copy(scheduleLogs = scheduleLogs)
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