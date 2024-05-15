package com.example.mountainmate.ui.itemlist


sealed class ItemListUiAction {
    data class DeleteItem(val itemId: Int) : ItemListUiAction()

    data class CheckItem(val itemId: Int) : ItemListUiAction()
}