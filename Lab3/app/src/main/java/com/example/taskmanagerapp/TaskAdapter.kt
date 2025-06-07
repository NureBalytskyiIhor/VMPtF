package com.example.taskmanagerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.data.Task

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onDoneClick: (Task) -> Unit,
    private val onEditClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.taskTitle)
        val description: TextView = view.findViewById(R.id.taskDescription)
        val btnDone: Button = view.findViewById(R.id.btnDone)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.title.text = task.title
        holder.description.text = task.description

        // Стилізація: закреслений текст, якщо виконано
        holder.title.paint.isStrikeThruText = task.isDone

        holder.btnDone.setOnClickListener { onDoneClick(task) }
        holder.btnEdit.setOnClickListener { onEditClick(task) }
        holder.btnDelete.setOnClickListener { onDeleteClick(task) }
    }

    override fun getItemCount(): Int = tasks.size

    fun updateList(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }
}
