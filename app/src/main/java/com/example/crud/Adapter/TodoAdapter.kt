package com.example.crud.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.Model.Todo
import com.example.crud.R

class TodoAdapter(val context: Context, private val listener: iNotes) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    val todoNoteList = ArrayList<Todo>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtView = itemView.findViewById<TextView>(R.id.etName)
        val ivDel = itemView.findViewById<ImageView>(R.id.ivDelete)
        val ivEdt = itemView.findViewById<ImageView>(R.id.ivEdit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_items, parent, false)
        )
        view.ivDel.setOnClickListener {
            listener.onItemDelClick(todoNoteList[view.adapterPosition])
        }
        view.ivEdt.setOnClickListener {
            listener.onItemEditClick(todoNoteList[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = todoNoteList[position]
        holder.txtView.text = data.title

    }

    override fun getItemCount(): Int {
        return todoNoteList.size
    }

    fun updateList(todoList: ArrayList<Todo>) {
        todoNoteList.clear()
        todoNoteList.addAll(todoList)

        notifyDataSetChanged()
    }


}

interface iNotes {
    fun onItemDelClick(todo: Todo)
    fun onItemEditClick(todo: Todo)
}