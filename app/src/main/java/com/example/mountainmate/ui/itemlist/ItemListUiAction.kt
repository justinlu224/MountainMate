package com.example.mountainmate.ui.itemlist


sealed class ItemListUiAction {
    data class DeleteItem(val itemData: ItemData) : ItemListUiAction()

    data class CheckItem(val itemId: Int) : ItemListUiAction()
}