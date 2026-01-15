package com.example.viikkotehtava1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.viikkotehtava1.ui.theme.Viikkotehtava1Theme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.setValue
import com.example.viikkotehtava1.domain.mockTasks
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.viikkotehtava1.domain.Task
import com.example.viikkotehtava1.domain.addTask


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikkotehtava1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(height = 30.dp))
    Text (text = "Task List")
    Spacer(modifier = Modifier.height(height = 16.dp))
    HomeScreen ()
}

@Composable
fun NameTextField(
    name: String,
    onNameChange: (String) -> Unit
) {
    OutlinedTextField(value = name,
        onValueChange = onNameChange,
        label = { Text(text = "Nimi") })
}

@Composable
fun HomeScreen() {
    var name by remember { mutableStateOf("") }
    var taskList by remember { mutableStateOf(mockTasks) }
    var showOnlyDone by remember { mutableStateOf(false) }

    val visibleTasks = if (showOnlyDone) {
        taskList.filter { it.done }
    } else {
        taskList
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        visibleTasks.forEach { task ->
            Row(
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Id: ${task.id} Title: ${task.title} DueDate: ${task.dueDate} Done: ${task.done}",
                    modifier = Modifier.weight(1f)
                )

                Button(onClick = {
                    taskList = taskList.map { t ->
                        if (t.id == task.id) t.copy(done = !t.done) else t
                    }
                }) {
                    Text("Toggle done")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = {
                    val newTask = Task(
                        id = taskList.size + 1,
                        title = "Uusi task",
                        description = "Description",
                        priority = 1,
                        dueDate = "2023-10-31",
                        done = false
                    )
                    taskList = addTask(taskList, newTask)
                }
            ) {
                Text("Lisää uusi task")
            }

            Button(
                onClick = {
                    taskList = taskList.sortedBy { it.dueDate }
                }
            ) {
                Text("Järjestä päivämäärän mukaan")
            }

            Button(
                onClick = {
                    showOnlyDone = !showOnlyDone
                }) {
                Text(if (showOnlyDone) "Näytä kaikki" else "Näytä vain tehdyt")
            }
        }
    }
}








