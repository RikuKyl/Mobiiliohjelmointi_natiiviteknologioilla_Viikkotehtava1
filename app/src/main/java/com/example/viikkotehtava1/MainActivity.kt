package com.example.viikkotehtava1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import java.time.LocalDate
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtava1.ViewModels.TaskViewModel
import com.example.viikkotehtava1.domain.Task
import com.example.viikkotehtava1.ui.theme.Viikkotehtava1Theme
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Viikkotehtava1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val taskViewModel: TaskViewModel = viewModel()
                    var taskName by remember { mutableStateOf("") }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
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
                                    val currentDate = LocalDate.now()
                                    val formattedDate =
                                        currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                    taskViewModel.addTask(
                                        Task(
                                            id = taskViewModel.tasks.size + 1,
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

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                            Button(onClick = {
                                taskViewModel.sortByDueDate()
                            }) {
                                Text("Järjestä päivämäärän mukaan")
                            }

                            Button(onClick = {
                                taskViewModel.toggleShowOnlyDone()
                            }) {
                                Text(
                                    text = "Filtteröi tehdyt"
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(vertical = 8.dp)
                        ) {
                            items(taskViewModel.tasks) { task ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),

                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        Checkbox(
                                            checked = task.done,
                                            onCheckedChange = { taskViewModel.toggleDone(task.id) }
                                        )

                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(text = task.title)
                                            Text(text = "DueDate: ${task.dueDate}")
                                        }

                                        Button(onClick = { taskViewModel.removeTask(task.id) }) {
                                            Text("Poista")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}