package com.jmdev.app.todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmdev.app.todo.data.Repository
import com.jmdev.app.todo.model.TaskModel
import com.jmdev.app.todo.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskModel>?>(null)
    val tasks = _tasks

    private val _completedTasks = MutableStateFlow<List<TaskModel>?>(null)
    val completedTasks = _completedTasks

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val taskList = repository.getTasks()
            if (taskList.isNotEmpty()) {
                val taskModels = taskList.filter { !it.taskState }.map { it.toModel() }
                _tasks.value = taskModels
            } else {
                _tasks.value = emptyList()
            }
        }
    }

    fun createTask(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createTask(name)
            loadTasks()
        }
    }

    fun setCompleted(taskId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setCompleted(taskId)
            loadTasks()
        }
    }

    fun getCompletedTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasksCompletedList = repository.getCompletedTasks()
            if (tasksCompletedList.isNotEmpty()) {
                val taskModels = tasksCompletedList.map { it.toModel() }
                _completedTasks.value = taskModels
            } else {
                _completedTasks.value = emptyList()
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(id)
            getCompletedTasks()
            loadTasks()
        }
    }

    fun deleteCompletedTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCompletedTasks()
            getCompletedTasks()
        }
    }
}