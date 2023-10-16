package com.example.crud.Repository

import androidx.lifecycle.LiveData
import com.example.crud.DataBase.TodoDao
import com.example.crud.Model.Todo

class TodoRepo(val todoDao: TodoDao) {

//    val allTodoNotes : LiveData<List<Todo>> = todoDao.getTodoNotes()


    fun allTodoNotes(): LiveData<List<Todo>> {
         return todoDao.getTodoNotes()
    }


    suspend fun insetTodoNote(todoNote: Todo) {
        todoDao.insertTodoNotes(todoNote)
    }

    suspend fun deleteTodoNote(todoNote: Todo) {
        todoDao.deleteTodoNotes(todoNote)
    }

    suspend fun updateTodo(todoNote: Todo) {
        todoDao.updateTodoNotes(todoNote)
    }


}