package com.example.mountainmate.ui.schedule

import android.Manifest
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mountainmate.ui.navhost.RouteItemList
import com.example.mountainmate.ui.navhost.RouteScheduleDetail
import com.example.mountainmate.ui.theme.MountainMateTheme
import com.example.mountainmate.util.DateUtil

sealed class ScheduleDetailAction {
    data object CheckUserLocationPermission : ScheduleDetailAction()
    data class ShowRecordDialog(val show: Boolean) : ScheduleDetailAction()

    data class AccessLocalPermission(val access: Boolean) : ScheduleDetailAction()
    data class SendRecord(val text: String) : ScheduleDetailAction()
}

@Composable
fun ScheduleDetailScreen(
    scheduleDetail: RouteScheduleDetail,
    navHostController: NavHostController,
    viewModel: ScheduleDetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    val locationPermissionRequest = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                println("@@@@@@: access granted fine")
                viewModel.onAction(ScheduleDetailAction.AccessLocalPermission(true))
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                println("@@@@@@: access granted coarse")
                viewModel.onAction(ScheduleDetailAction.AccessLocalPermission(true))
            }

            else -> {
                // No location access granted.
                println("@@@@@@: not access ")
                viewModel.onAction(ScheduleDetailAction.AccessLocalPermission(false))

            }
        }
        viewModel.onAction(ScheduleDetailAction.ShowRecordDialog(true))

    }

    LaunchedEffect(key1 = Unit) {
        viewModel.scheduleId = scheduleDetail.scheduleId
        viewModel.getScheduleLogs(scheduleDetail.scheduleId)

        viewModel.eventsFlow.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { event ->
                when (event) {
                    is ScheduleDetailViewModel.Event.CheckUserLocationPermission -> {
                        locationPermissionRequest.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }
                }
            }
    }



    Content(
        scheduleDetail,
        navHostController,
        uiState,
        viewModel::onAction
    )

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Content(
    scheduleDetail: RouteScheduleDetail,
    navHostController: NavHostController,
    uiState: ScheduleDetailUiState,
    onAction: (ScheduleDetailAction) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
                title = {
                    Text(text = scheduleDetail.title)
                },
                navigationIcon = {
                    Icon(modifier = Modifier.padding(start = 8.dp), imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    color = MaterialTheme.colorScheme.tertiary,
                    style = TextStyle(fontWeight = FontWeight(600)),
                    fontSize = 16.sp,
                    text = "裝備檢查表"
                )

                Icon(
                    modifier = Modifier.clickable {
                        navHostController.navigate(RouteItemList(scheduleDetail.scheduleId))
                    },
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "ItemListPage",
                    tint = MaterialTheme.colorScheme.tertiary,
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.tertiary,
                style = TextStyle(fontWeight = FontWeight(600)),
                fontSize = 16.sp,
                text = "紀錄"
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(uiState.scheduleLogs.size) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)

                    ) {
                        Text(
                            text = "・",
                            color = MaterialTheme.colorScheme.tertiary,
                        )

                        Column {
                            Row {
                                Text(
                                    text = DateUtil.convertTimeStampToStringWithYearAndTime(uiState.scheduleLogs[it].time),
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 10.sp
                                )

                                if (uiState.scheduleLogs[it].latitude != null && uiState.scheduleLogs[it].longitude != null) {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "(${uiState.scheduleLogs[it].latitude} , ${uiState.scheduleLogs[it].longitude})",
                                        color = MaterialTheme.colorScheme.tertiary,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                            Text(
                                text = uiState.scheduleLogs[it].description,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }

            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                onAction(ScheduleDetailAction.CheckUserLocationPermission)

            }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }

        RecordDialog(
            openDialog = uiState.showDialog,
            onDismiss = {
                onAction(ScheduleDetailAction.ShowRecordDialog(false))
            },
            onConfirm = {
                onAction(ScheduleDetailAction.SendRecord(it))
            }
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewScheduleDetailScreen() {
    MountainMateTheme {
        ScheduleDetailScreen(
            scheduleDetail = RouteScheduleDetail(title = "DDD", scheduleId = 0),
            rememberNavController()
        )
    }
}