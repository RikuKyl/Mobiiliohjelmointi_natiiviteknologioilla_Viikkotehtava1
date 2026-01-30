package com.example.viikkotehtava1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtava1.model.Task
import com.example.viikkotehtava1.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {

    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()

    var taskName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Task List",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text("Taskin nimi") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (taskName.isNotBlank()) {

                    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val formattedDate = formatter.format(Date())

                    viewModel.addTask(
                        Task(
                            id = tasks.size + 1,
                            title = taskName,
                            description = "",
                            priority = 1,
                            dueDate = formattedDate,
                            done = false
                        )
                    )

                    taskName = ""
                }
            }) {
                Text("Lisää")
            }
        }

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

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.selectTask(task) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Checkbox(
                            checked = task.done,
                            onCheckedChange = {
                                viewModel.toggleDone(task.id)
                            }
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = task.title)
                            Text(text = task.description)
                            Text(text = "DueDate: ${task.dueDate}")
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
            onUpdate = { viewModel.updateTask(it) }
        )
    }
}