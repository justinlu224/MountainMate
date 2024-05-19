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
    private var scheduleId: Int? = null
    fun updateCheckItemList(scheduleId: Int) {
        this.scheduleId = scheduleId
        viewModelScope.launch {
            val checkItemList = itemListRepository.getCheckItemList(scheduleId)
            _uiState.update {
                it.copy(checkItemList = checkItemList)
            }
        }
    }

    private fun deleteItem(itemData: ItemData) {
        viewModelScope.launch {
            itemListRepository.deleteCheckItem(itemData)
            scheduleId?.let { updateCheckItemList(it) }
        }
    }

    fun onAction(action : ItemListUiAction) {
        when(action) {
            is ItemListUiAction.CheckItem -> {
                updateCheckState(action.itemId, action.isCheck)
            }
            is ItemListUiAction.DeleteItem -> {
                deleteItem(action.itemData)
            }
        }
    }

    private fun updateCheckState(itemId: Int, check: Boolean) {
        viewModelScope.launch {
            itemListRepository.updateCheckState(itemId, check)
            scheduleId?.let { updateCheckItemList(it) }
        }
    }
}