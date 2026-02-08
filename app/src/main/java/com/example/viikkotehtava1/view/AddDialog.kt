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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtava1.model.Task
import com.example.viikkotehtava1.viewmodel.TaskViewModel


@Composable
fun AddDialog(
    onClose: () -> Unit,
    onUpdate: (Task) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Add Task") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                TextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due date (yyyy-MM-dd)") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onUpdate(
                        Task(
                            id = 0,
                            title = title,
                            description = description,
                            priority = 1,
                            dueDate = dueDate,
                            done = false
                        )
                    )
                    onClose()
                },
                enabled = title.isNotBlank()
            ) {
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