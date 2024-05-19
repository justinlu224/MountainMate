package com.example.mountainmate.ui.itemlist

import androidx.compose.runtime.Immutable

@Immutable
data class ItemListUiState(val checkItemList: List<SectionData> = mutableListOf())