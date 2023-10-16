package com.example.crud.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crud.DataBase.TodoDb
import com.example.crud.Model.Todo
import com.example.crud.Repository.TodoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    val repository: TodoRepo
    val allTodoNotes: LiveData<List<Todo>>

    init {
        val dao = TodoDb.getDataBaseInstance(application).getTodoNotes()
        repository = TodoRepo(dao)
        allTodoNotes = repository.allTodoNotes()
    }

     fun addTodoNote(todoNote: Todo) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insetTodoNote(todoNote)
        }

    //    fun getAllTodoNotes(): LiveData<List<Todo>> {
//        return repository.todoGetAllNotes()
//    }
     fun deleteTodoNote(todoNote: Todo) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodoNote(todoNote)
        }

    fun updateTodoNotes(todo: Todo) =  viewModelScope.launch(Dispatchers.IO) {
        repository.updateTodo(todo)
    }
}