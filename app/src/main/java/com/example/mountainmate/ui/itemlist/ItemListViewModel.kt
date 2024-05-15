package com.example.mountainmate.ui.itemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val itemListRepository: ItemListRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ItemListUiState>(ItemListUiState())
    val uiState = _uiState.asStateFlow()
    fun updateCheckItemList(scheduleId: Int) {
        viewModelScope.launch {
            val checkItemList = itemListRepository.getCheckItemList(scheduleId)
            _uiState.update {
                it.copy(checkItemList = checkItemList)
            }
        }
    }
}