package com.example.composestate

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.composestate.ui.model.WellnessTask

class WellnessViewModel : ViewModel() {
    private val _tasks = getWellnessTaskList().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) {
        val index = tasks.indexOfFirst {
            it.id == item.id
        }.takeIf { it != -1 } ?: 0
        _tasks[index] = item.copy(checked = checked)
    }

    fun remove(wellnessTask: WellnessTask) {
        _tasks.remove(wellnessTask)
    }

    private fun getWellnessTaskList() = List(30) {
        WellnessTask(
            it,
            "Task # $it"
        )
    }
}