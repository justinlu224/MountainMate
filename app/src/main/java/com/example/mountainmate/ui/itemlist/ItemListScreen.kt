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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import com.example.mountainmate.ui.theme.MountainMateTheme

data class SectionData(
    val title: String,
    val items: List<ItemData>,
    val isExpend: MutableState<Boolean> = mutableStateOf(true)
)

data class ItemData(
    val title: String,
    val isChecked: Boolean
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ItemListScreen() {

    val sections = listOf(
        SectionData("Section 1", listOf(ItemData("Item 1", false), ItemData("Item 2", false), ItemData("Item 3", false))),
        SectionData("Section 2", listOf(ItemData("Item 4", false), ItemData("Item 5", false), ItemData("Item 6", false))),
        SectionData("Section 3", listOf(ItemData("Item 7", false), ItemData("Item 8", false), ItemData("Item 9", false))),
        SectionData("Section 4", listOf(ItemData("Item 10", false), ItemData("Item 11", false), ItemData("Item 12", false))),
        SectionData("Section 5", listOf(ItemData("Item 13", false), ItemData("Item 14", false), ItemData("Item 15", false))),
        SectionData("Section 6", listOf(ItemData("Item 16", false), ItemData("Item 17", false), ItemData("Item 18", false))),
        SectionData("Section 7", listOf(ItemData("Item 19", false), ItemData("Item 20", false), ItemData("Item 21", false))),
        SectionData("Section 8", listOf(ItemData("Item 22", false), ItemData("Item 23", false), ItemData("Item 24", false))),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        sections.forEach { sectionData ->

            stickyHeader {
                Section(sectionData = sectionData.title) {
                    sectionData.isExpend.value = !sectionData.isExpend.value
                }
            }
            if (sectionData.isExpend.value) {
                items(sectionData.items.size) {
                    SwipeItem(sectionData.items[it].title)
                }
            }
        }
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
            .padding(vertical = 20.dp)
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
private fun SwipeItem(s: String) {

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
            isShow = false
        }
    }


    if (isShow) {
        SwipeToDismissBox(
            state = state,
            enableDismissFromStartToEnd = false,
            backgroundContent = {
                Text(
                    textAlign = TextAlign.End,
                    text = "Delete",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                        .padding(vertical = 20.dp)
                )
            }) {

            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false, onCheckedChange = {
                        // todo
                    }
                )

                Text(
                    text = s, modifier = Modifier
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
        ItemListScreen()
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ItemListScreenPreviewNight() {
    MountainMateTheme {
        ItemListScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun SectionPreview() {
    MountainMateTheme {
        Section("ssssss")
    }
}