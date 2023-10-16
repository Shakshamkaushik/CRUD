package com.example.crud.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crud.Model.Todo


@Database(entities = [Todo::class], version = 1)
abstract class TodoDb : RoomDatabase() {

    abstract fun getTodoNotes(): TodoDao

    companion object {
        @Volatile
        var INSTANCE: TodoDb? = null

        fun getDataBaseInstance(context: Context): TodoDb {
            val temporaryInstance = INSTANCE
            if (temporaryInstance != null) {
                return temporaryInstance
            } else {
                synchronized( this) {
                    val roomDbInstance =
                        Room.databaseBuilder(context, TodoDb::class.java, "TodoNotes").build()

                    INSTANCE  = roomDbInstance
                    return  roomDbInstance
                }
            }
        }
    }
}