package com.example.viikkotehtava1.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikkotehtava1.domain.Task
import com.example.viikkotehtava1.domain.mockTasks
import com.example.viikkotehtava1.domain.addTask
import com.example.viikkotehtava1.domain.toggleDoneById
import com.example.viikkotehtava1.domain.removeTask
import com.example.viikkotehtava1.domain.sortByDueDate
import com.example.viikkotehtava1.domain.filterByDone

class TaskViewModel : ViewModel() {

    var tasks by mutableStateOf(listOf<Task>())
        private set

    private var allTasks = listOf<Task>()
    private var showOnlyDone = false

    init {
        allTasks = mockTasks
        tasks = allTasks
    }

    fun addTask(task: Task) {
        allTasks = addTask(allTasks, task)
        tasks = allTasks
    }

    fun toggleDone(id: Int) {
        allTasks = toggleDoneById(allTasks, id)
        tasks = allTasks
    }

    fun removeTask(id: Int) {
        allTasks = removeTask(allTasks, id)
        tasks = allTasks
    }

    fun sortByDueDate() {
        tasks = sortByDueDate(tasks)
    }

    fun toggleShowOnlyDone() {
        showOnlyDone = !showOnlyDone
        updateTasks()
    }

    fun isShowingOnlyDone(): Boolean = showOnlyDone

    private fun updateTasks() {
        tasks = if (showOnlyDone) {
            filterByDone(allTasks, true)
        } else {
            allTasks
        }
    }

}