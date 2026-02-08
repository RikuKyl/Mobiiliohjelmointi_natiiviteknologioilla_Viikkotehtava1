package com.example.viikkotehtava1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtava1.model.Task
import com.example.viikkotehtava1.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
    onNavigateCalendar: () -> Unit = {},
    onNavigateSettings: () -> Unit = {}
) {

    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val addTaskFlag by viewModel.addTaskDialogVisible.collectAsState()



    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        TopAppBar(
            title = { Text("Task List") },
            actions = {
                IconButton(onClick = onNavigateCalendar) {
                    Icon(
                        Icons.Default.CalendarMonth,
                        contentDescription = "Calendar"
                    )
                }

                IconButton(onClick = onNavigateSettings) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings")
                }

                IconButton(onClick = {
                    viewModel.addTaskDialogVisible.value = true
                }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add task"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))




        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { viewModel.sortByDueDate() }) {
                Text("Järjestä päivämäärän mukaan")
            }
            Button(onClick = { viewModel.toggleShowOnlyDone() }) {
                Text("Filtteröi tehdyt")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.selectTask(task) }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Checkbox(
                            checked = task.done,
                            onCheckedChange = { viewModel.toggleDone(task.id) }
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            Text(task.title, style = MaterialTheme.typography.titleMedium)
                            Text(task.description)
                            Text("Due: ${task.dueDate}")
                        }

                        Button(onClick = { viewModel.removeTask(task.id) }) {
                            Text("Poista")
                        }
                    }
                }
            }
        }
    }

    if (selectedTask != null) {
        DetailDialog(
            task = selectedTask!!,
            onClose = { viewModel.closeDialog() },
            onUpdate = { viewModel.updateTask(it) },
            onDelete = { viewModel.removeTask(it) }
        )
    }

    if (addTaskFlag) {
        AddDialog(
            onClose = { viewModel.addTaskDialogVisible.value = false },
            onUpdate = { viewModel.addTask(it) }
        )
    }
}