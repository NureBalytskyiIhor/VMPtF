package com.example.taskmanagerapp.data

data class Task(
    val id: Long,
    var title: String,
    var description: String,
    var isDone: Boolean = false
)