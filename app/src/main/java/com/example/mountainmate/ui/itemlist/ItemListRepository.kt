package com.example.mountainmate.ui.itemlist

import com.example.mountainmate.data.datasource.LocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemListRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    suspend fun getCheckItemList(scheduleId: Int): List<SectionData> {
        val checkItems = localDataSource.getCheckItemList(scheduleId)
       return checkItems.groupBy { it.category }
            .map {
                SectionData(
                    title = it.key.name,
                    items = it.value.map { checkItemEntity ->
                        ItemData(
                            id = checkItemEntity.id,
                            scheduleId = checkItemEntity.scheduleId,
                            itemName = checkItemEntity.itemName,
                            isChecked = checkItemEntity.isChecked,
                            category = checkItemEntity.category
                        )
                    }
                )
            }
    }

    suspend fun deleteCheckItem(itemData: ItemData) {
        val checkItemEntity = itemData.convertToCheckItemEntity()
        localDataSource.deleteCheckItem(checkItemEntity)
    }

    suspend fun updateCheckState(itemId: Int, iscCheck: Boolean) {
        localDataSource.updateCheckState(itemId, iscCheck)
    }

}