package com.example.composestate

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onClose: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = taskName, modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

// Don't need this stateful method anymore
//@Composable
//fun WellnessTaskItem(
//    modifier: Modifier = Modifier,
//    taskName: String,
//    onClose: () -> Unit
//) {
//    var checked by rememberSaveable { mutableStateOf(false) }
//
//    WellnessTaskItem(
//        taskName = taskName,
//        checked = checked,
//        onClose = onClose,
//        onCheckedChange = {
//            checked = it
//        }
//    )
//}