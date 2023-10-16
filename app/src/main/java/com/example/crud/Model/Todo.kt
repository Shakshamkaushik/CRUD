package com.example.crud.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TodoNotes")
data class Todo(
    val title: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0)
