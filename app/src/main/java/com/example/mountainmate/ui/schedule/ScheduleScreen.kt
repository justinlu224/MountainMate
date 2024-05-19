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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ScheduleScreen(
    navController: NavHostController,
    scheduleViewModel: ScheduleViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val uiState by scheduleViewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        val openDialog = remember {
            mutableStateOf(false)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(uiState.scheduleList) {
                CardScheduleItem(scheduleEntity = it, navController = navController)
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
    }
}

@Preview
@Composable
fun PreviewListScreen() {
    ScheduleScreen(
        rememberNavController()
    )
}