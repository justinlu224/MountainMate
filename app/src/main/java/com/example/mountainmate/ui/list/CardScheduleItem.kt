package com.example.mountainmate.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.Dialog

@Composable
fun CardScheduleItem(i: Int) {
    Surface(
        modifier = Modifier
            .size(120.dp, 120.dp),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.secondary,
        shadowElevation = 6.dp,
        tonalElevation = 6.dp,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSecondary)
    ) {
        Box(modifier = Modifier
            ) {
            Text(
                text = "Item $i",
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
    CardScheduleItem(1)
}