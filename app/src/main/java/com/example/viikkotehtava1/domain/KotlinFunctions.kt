package com.example.viikkotehtava1.domain

fun addTask(list: List<Task>, newTask: Task): List<Task> {
    return list + newTask
}

fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { it.dueDate }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

fun toggleDoneById(list: List<Task>, id: Int): List<Task> {
    return list.map { task ->
        if (task.id == id) {
            task.copy(done = !task.done)
        } else {
            task
        }
    }
}

fun removeTask(list: List<Task>, id: Int): List<Task> {
    return list.filter {it.id != id}
}