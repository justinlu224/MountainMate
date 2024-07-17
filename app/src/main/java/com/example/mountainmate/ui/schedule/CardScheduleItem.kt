package com.example.mountainmate.ui.schedule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mountainmate.data.room.ScheduleEntity
import com.example.mountainmate.ui.navhost.RouteScheduleDetail

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardScheduleItem(
    scheduleEntity: ScheduleEntity,
    navController: NavHostController,
    onAction: (ScheduleUiAction) -> Unit = { }
) {
    Surface(
        modifier = Modifier
            .size(120.dp, 120.dp),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.secondary,
        shadowElevation = 6.dp,
        tonalElevation = 6.dp,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSecondary)
    ) {
        Box(
            modifier = Modifier
            .combinedClickable(
                onClick = {
                    navController.navigate(RouteScheduleDetail(scheduleEntity.name, scheduleEntity.id))
                },
                onLongClick = {
                    onAction(ScheduleUiAction.OpenDeleteDialog(scheduleEntity.id))
                }
            )

            ) {
            Text(
                text = scheduleEntity.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardScheduleItemPreview() {

    CardScheduleItem(ScheduleEntity(0,"dasdasdads"), rememberNavController())
}