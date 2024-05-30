package com.example.mountainmate.ui.itemlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mountainmate.data.room.Category
import com.example.mountainmate.data.room.CheckItemEntity
import com.example.mountainmate.ui.theme.MountainMateTheme

data class SectionData(
    val title: String,
    val items: List<ItemData>,
    val isExpend: MutableState<Boolean> = mutableStateOf(true)
)

data class ItemData(
    val id: Int = 0,
    val scheduleId: Int,
    val itemName: String,
    val isChecked: Boolean = false,
    val category: Category,
)

fun ItemData.convertToCheckItemEntity(): CheckItemEntity {
    return CheckItemEntity(
        id = id,
        scheduleId = scheduleId,
        itemName = itemName,
        isChecked = isChecked,
        category = category
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ItemListScreen(
    scheduleId: Int,
    viewModel: ItemListViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        println("@@@@@@@: scheduleId: $scheduleId")
        viewModel.updateCheckItemList(scheduleId)
    }

    val openDialog = remember {
        mutableStateOf(false)
    }

    val uiState by viewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            uiState.checkItemList.forEach { sectionData ->

                stickyHeader {
                    Section(sectionData = sectionData.title) {
                        sectionData.isExpend.value = !sectionData.isExpend.value
                    }
                }
                if (sectionData.isExpend.value) {
                    items(sectionData.items.size) {
                        SwipeItem(
                            sectionData.items[it],
                            viewModel::onAction
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                openDialog.value = true
            }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }

        AddItemDialog(
            openDialog = openDialog,
            menuItems = Category.values().toList(),
            viewModel::onAction
        )

    }
}

@Composable
fun Section(
    sectionData: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 12.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = sectionData, textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SwipeItem(
    itemData: ItemData,
    onAction: (ItemListUiAction) -> Unit
) {

    val state = rememberSwipeToDismissBoxState(
        positionalThreshold = {
            it / 2
        }
    )

    var isShow by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = state.currentValue) {
        println("@@@@@@: stete: ${state.targetValue}")
        if (state.currentValue == SwipeToDismissBoxValue.EndToStart && state.progress == 1f) {
            println("@@@@ DismissedToEnd")
            onAction(ItemListUiAction.DeleteItem(itemData = itemData))
            isShow = false
        }
    }

    if (isShow) {
        SwipeToDismissBox(
            state = state,
            enableDismissFromStartToEnd = false,
            backgroundContent = {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                        .padding(vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        textAlign = TextAlign.End,
                        text = "Delete",
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                    )
                }
            }) {

            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = itemData.isChecked, onCheckedChange = {
                        onAction(ItemListUiAction.CheckItem(itemId = itemData.id, isCheck = it))
                    }
                )

                Text(
                    text = itemData.itemName, modifier = Modifier
                        .fillMaxWidth()
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemListScreenPreview() {
    MountainMateTheme {
        ItemListScreen(1)
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ItemListScreenPreviewNight() {
    MountainMateTheme {
        ItemListScreen(1)
    }
}

@Preview(showBackground = true)
@Composable
fun SectionPreview() {
    MountainMateTheme {
        Section("ssssss")
    }
}

@Preview
@Composable
fun PreviewSwipeItem() {
    MountainMateTheme {
        SwipeItem(
            ItemData(
            scheduleId = 1,
            itemName = "item1",
            category = Category.FOOD
        )
        ) {}
    }
}