package com.example.mountainmate.ui.schedule

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mountainmate.ui.navhost.RouteItemList
import com.example.mountainmate.ui.navhost.RouteScheduleDetail
import com.example.mountainmate.ui.theme.MountainMateTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleDetailScreen(
    scheduleDetail: RouteScheduleDetail,
    navHostController: NavHostController
    ) {
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
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
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

            items(10) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)

                ) {
                    Text(
                        text = "・",
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                    // fake ui
                    Text(
                        text = "2024/01/01 12:12",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 10.sp
                    )
                    // fake ui
                    Text(
                        text = "到達營地",
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

        }


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