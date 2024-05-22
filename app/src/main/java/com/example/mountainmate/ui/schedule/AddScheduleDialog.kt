package com.example.mountainmate.ui.schedule

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mountainmate.ui.theme.MountainMateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScheduleDialog(
    openDialog: MutableState<Boolean>,
    onAction: (ScheduleUiAction) -> Unit = {}
) {
    if (!openDialog.value) return

    var openMenu by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = {
            openDialog.value = false
        }
    ) {
        var typetext by remember { mutableStateOf("") }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(8.dp)),
        ) {

            val (topSpacer, title, textField, btnConfirm, bottomSpacer) = createRefs()

            Spacer(modifier = Modifier
                .height(16.dp)
                .constrainAs(topSpacer) {
                    top.linkTo(parent.top)
                })
            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(topSpacer.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                color = MaterialTheme.colorScheme.onSecondary,
                text = "新增行程",
                style = MaterialTheme.typography.titleLarge
            )
            TextField(
                modifier = Modifier
                    .constrainAs(textField) {
                        top.linkTo(title.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(8.dp)),
                value = typetext,
                onValueChange = {
                    typetext = it
                }
            )

            Button(
                enabled = typetext.isNotEmpty(),
                onClick = {
                    openDialog.value = false
                    onAction(ScheduleUiAction.AddSchedule(typetext))
                },
                modifier = Modifier
                    .constrainAs(btnConfirm) {
                        top.linkTo(textField.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text = "確認",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .constrainAs(bottomSpacer) {
                        top.linkTo(btnConfirm.bottom)
                    }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddScheduleDialogPreview() {
    val openDialog = remember { mutableStateOf(true) }
    MountainMateTheme {
        AddScheduleDialog(
            openDialog
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddScheduleDialogPreviewNight() {
    val openDialog = remember { mutableStateOf(true) }
    MountainMateTheme {
        AddScheduleDialog(
            openDialog
        )
    }
}