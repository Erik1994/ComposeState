package com.example.composestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composestate.ui.model.WellnessTask
import com.example.composestate.ui.theme.ComposeStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessScreen()
                }
            }
        }
    }
}

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessScreenViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StateFullCounter(modifier)
        WellnessTasksList(
            list = wellnessScreenViewModel.tasks,
            onCloseClick = { wellnessScreenViewModel.remove(it) },
            onCheckedTask = { wellnessTask, isChecked ->
                wellnessScreenViewModel.changeTaskChecked(wellnessTask, isChecked)
            }
        )
    }
}

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onCloseClick: (WellnessTask) -> Unit,
    onCheckedTask: (WellnessTask, Boolean) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseClick(task) }
            )
        }
    }
}

@Composable
fun StateFullCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    StatelessCounter(modifier = modifier, count = count) {
        ++count
    }
}

@Composable
fun StatelessCounter(
    modifier: Modifier = Modifier,
    count: Int,
    onIncrement: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
//            var showTask by remember { mutableStateOf(true) }
//            if (showTask) {
//                WellnessTaskItem(
//                    taskName = "Have you taken your 15 minutes walk today?",
//                    onClose = { showTask = false })
//            }
            // This text is present if the button has been clicked
            // at least once; absent otherwise
            Text("You've had $count glasses.")
        }
//        if (count in 2..4 || count in 7..9) {
//            var justCount by remember { mutableStateOf(true) }
//            if (count == 3) {
//                justCount = false
//            }
//            Text(text = "JustCount $justCount for test.")
//        }
//        Row(modifier = Modifier.padding(top = 8.dp)) {
//            Button(onClick = { count++ }, enabled = count < 10) {
//                Text("Add one")
//            }
////            Button(onClick = { count = 0 }, Modifier.padding(start = 8.dp)) {
////                Text("Clear water count")
////            }
//        }

        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeStateTheme {
        Greeting("Android")
    }
}