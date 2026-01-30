package com.example.viikkotehtava1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viikkotehtava1.model.Task
import com.example.viikkotehtava1.model.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class TaskViewModel : ViewModel() {

    private val _allTasks = MutableStateFlow<List<Task>>(emptyList())
    private val _showOnlyDone = MutableStateFlow(false)

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    val tasks: StateFlow<List<Task>> =
        combine(_allTasks, _showOnlyDone) { tasks, showOnlyDone ->
            if (showOnlyDone) {
                tasks.filter { it.done }
            } else {
                tasks
            }
        } .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        _allTasks.value = mockTasks
    }

    fun addTask(task: Task) {
        _allTasks.value += task
    }

    fun toggleDone(id: Int) {
        _allTasks.value = _allTasks.value.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(id: Int) {
        _allTasks.value = _allTasks.value.filter { it.id != id }
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    fun updateTask(updated: Task) {
        _allTasks.value = _allTasks.value.map {
            if (it.id == updated.id) updated else it
        }
        _selectedTask.value = null
    }

    fun closeDialog() {
        _selectedTask.value = null
    }

    fun sortByDueDate() {
        _allTasks.value = _allTasks.value.sortedBy { it.dueDate }
    }

    fun toggleShowOnlyDone() {
        _showOnlyDone.value = !_showOnlyDone.value
    }
}