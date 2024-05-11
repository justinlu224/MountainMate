package com.example.mountainmate.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

fun Int.pxToDp(density: Density): Dp {
    return with(density) {
        toDp()
    }
}