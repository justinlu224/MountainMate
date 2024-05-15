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
                            title = checkItemEntity.itemName,
                            isChecked = checkItemEntity.isChecked
                        )
                    }
                )
            }
    }

}