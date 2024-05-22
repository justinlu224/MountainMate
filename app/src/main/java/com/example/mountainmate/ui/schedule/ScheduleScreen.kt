package com.example.mountainmate.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ScheduleScreen(
    navController: NavHostController,
    scheduleViewModel: ScheduleViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val uiState by scheduleViewModel.uiState.collectAsStateWithLifecycle()
    val openDialog = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(uiState.scheduleList) {
                CardScheduleItem(
                    scheduleEntity = it,
                    navController = navController,
                    scheduleViewModel::onAction
                )
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

        AddScheduleDialog(
            openDialog = openDialog,
            scheduleViewModel::onAction
        )

        DeleteScheduleItem(uiState, scheduleViewModel::onAction)


    }
}

@Composable
fun DeleteScheduleItem(
    openDeleteDialog: ScheduleState,
    onAction: (ScheduleUiAction) -> Unit = { }
) {
    if (openDeleteDialog.openDeleteDialog) {
        AlertDialog(
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            title = {
                Text(
                    text = "確定要刪除行程嗎",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            onDismissRequest = {
                onAction(ScheduleUiAction.DismissDeleteDialog)
            }, confirmButton = {
                TextButton(onClick = {
                    onAction(ScheduleUiAction.DeleteSchedule)
                }) {
                    Text(
                        "確定",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            },

            dismissButton = {
                TextButton(onClick = {
                    onAction(ScheduleUiAction.DismissDeleteDialog)
                }) {
                    Text(
                        "取消",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            },
        )
    }
}

@Preview
@Composable
fun PreviewListScreen() {
    ScheduleScreen(
        rememberNavController()
    )
}