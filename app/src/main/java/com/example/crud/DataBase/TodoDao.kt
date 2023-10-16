package com.example.crud.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crud.Model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * from TodoNotes")
    fun getTodoNotes(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodoNotes(todo: Todo)

    @Delete
    suspend fun deleteTodoNotes(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodoNotes(todo: Todo)

}