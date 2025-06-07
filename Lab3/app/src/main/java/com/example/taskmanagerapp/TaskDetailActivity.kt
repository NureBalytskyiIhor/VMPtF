package com.example.taskmanagerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editDescription: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        editTitle = findViewById(R.id.editTitle)
        editDescription = findViewById(R.id.editDescription)
        saveButton = findViewById(R.id.saveButton)

        val existingTitle = intent.getStringExtra(MainActivity.EXTRA_TASK)
        val existingDescription = intent.getStringExtra(MainActivity.EXTRA_DESCRIPTION)
        val taskId = intent.getLongExtra(MainActivity.EXTRA_ID, -1L)

        if (existingTitle != null) {
            editTitle.setText(existingTitle)
            editDescription.setText(existingDescription ?: "")
            title = "Edit Task"
        } else {
            title = "New Task"
        }

        saveButton.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra(MainActivity.EXTRA_TASK, editTitle.text.toString())
                putExtra(MainActivity.EXTRA_DESCRIPTION, editDescription.text.toString())
                putExtra(MainActivity.EXTRA_ID, taskId)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
