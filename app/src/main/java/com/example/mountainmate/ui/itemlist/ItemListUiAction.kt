package com.example.mountainmate.ui.itemlist

import com.example.mountainmate.data.room.Category


sealed class ItemListUiAction {
    data class DeleteItem(val itemData: ItemData) : ItemListUiAction()

    data class CheckItem(val itemId: Int, val isCheck: Boolean) : ItemListUiAction()

    data class AddItem(val itemName: String, val category: Category) : ItemListUiAction()

}