package com.example.viikkotehtava1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.viikkotehtava1.model.Task


@Composable
fun DetailDialog(task: Task, onClose: () -> Unit, onUpdate: (Task) -> Unit) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var dueDate by remember { mutableStateOf(value = task.dueDate)}

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Muokkaa tehtävää") },
        text = {
            Column {
                TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                TextField(value = dueDate, onValueChange = { dueDate = it }, label = { Text(text = "DueDate")})
            }
        },
        confirmButton = {
            Button(onClick = {
                onUpdate(task.copy(title = title, description = description, dueDate = dueDate))
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onClose) {
                Text("Cancel")
            }
        }
    )
}