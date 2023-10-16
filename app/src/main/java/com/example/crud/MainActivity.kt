package com.example.crud


import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud.Adapter.TodoAdapter
import com.example.crud.Adapter.iNotes
import com.example.crud.Model.Todo
import com.example.crud.ViewModel.TodoViewModel
import com.example.crud.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), iNotes {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: TodoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvActivity.layoutManager = LinearLayoutManager(this)
        val adapter = TodoAdapter(this, this)
        binding.rvActivity.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TodoViewModel::class.java)

        viewModel.allTodoNotes.observe(this) { list ->
            list?.let {
                adapter.updateList(it as ArrayList<Todo>)
            }

        }

        binding.btnAdd.setOnClickListener {
            val todoText = binding.etTitleHeading.text.toString()
            if (todoText.isNotEmpty()) {
                viewModel.addTodoNote(Todo(todoText))
            }
            binding.etTitleHeading.text.clear()
        }


    }

    override fun onItemDelClick(todo: Todo) {
        viewModel.deleteTodoNote(todo)
    }

    override fun onItemEditClick(todo: Todo) {
        alertDialog(todo)
    }

    private fun alertDialog(todo: Todo) {
//        Toast.makeText(this, "INside onItemEdit", Toast.LENGTH_SHORT).show()

        val customAlertLayout  = Dialog(this)
        customAlertLayout.setContentView(R.layout.edit_alert_dialog)

        val btn = customAlertLayout.findViewById<Button>(R.id.btnAlert)
        val newText = customAlertLayout.findViewById<EditText>(R.id.etAlert)


        btn.setOnClickListener {
//            Toast.makeText(this, "btnAlert Clicked", Toast.LENGTH_SHORT).show()
            val text = newText.text.toString()
            if (text.isNotEmpty()) {
//                Toast.makeText(this, "Text $text", Toast.LENGTH_SHORT).show()
                viewModel.updateTodoNotes(Todo(text, todo.id))
            }
            customAlertLayout.dismiss()
        }

        customAlertLayout.show()
    }
}

