package com.example.taskmanagerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.data.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val taskList = mutableListOf<Task>()
    private lateinit var adapter: TaskAdapter
    private var nextId = 1L

    companion object {
        const val REQUEST_CODE_ADD = 1
        const val REQUEST_CODE_EDIT = 2
        const val EXTRA_TASK = "extra_task"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.addTaskButton)

        adapter = TaskAdapter(
            taskList,
            onDoneClick = { task ->
                task.isDone = !task.isDone
                adapter.notifyDataSetChanged()
            },
            onEditClick = { task ->
                val intent = Intent(this, TaskDetailActivity::class.java).apply {
                    putExtra(EXTRA_TASK, task.title)
                    putExtra(EXTRA_DESCRIPTION, task.description)
                    putExtra(EXTRA_ID, task.id)
                }
                startActivityForResult(intent, REQUEST_CODE_EDIT)
            },
            onDeleteClick = { task ->
                taskList.remove(task)
                adapter.notifyDataSetChanged()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this, TaskDetailActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val title = data.getStringExtra(EXTRA_TASK) ?: return
            val description = data.getStringExtra(EXTRA_DESCRIPTION) ?: ""
            val id = data.getLongExtra(EXTRA_ID, -1L)

            if (requestCode == REQUEST_CODE_ADD) {
                val newTask = Task(nextId++, title, description)
                taskList.add(newTask)
            } else if (requestCode == REQUEST_CODE_EDIT) {
                val task = taskList.find { it.id == id }
                task?.title = title
                task?.description = description
            }
            adapter.notifyDataSetChanged()
        }
    }
}
