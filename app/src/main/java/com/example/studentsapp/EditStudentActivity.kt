package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val checkboxStatus: CheckBox = findViewById(R.id.edit_student_activity_checked_button)
        val buttonCancel: Button = findViewById(R.id.edit_student_activity_cancel_button)
        val buttonSave: Button = findViewById(R.id.edit_student_activity_save_button)
        val buttonDelete: Button = findViewById(R.id.edit_student_activity_delete_button)
        val editTextName: EditText = findViewById(R.id.edit_student_activity_name_edit_text)
        val editTextId: EditText = findViewById(R.id.edit_student_activity_id_edit_text)
        val editTextPhone: EditText = findViewById(R.id.edit_student_activity_phone_edit_text)
        val editTextAddress: EditText = findViewById(R.id.edit_student_activity_address_edit_text)
        val textViewSaveMessage: TextView = findViewById(R.id.edit_student_activity_save_message_text_view)

        val studentName = intent.getStringExtra("studentName")
        val studentId = intent.getStringExtra("studentId")
        val studentPhone = intent.getStringExtra("studentPhone")
        val studentAddress = intent.getStringExtra("studentAddress")
        val studentChecked = intent.getBooleanExtra("isChecked", false)

        studentName?.let { editTextName.setText(it) }
        studentId?.let { editTextId.setText(it) }
        studentPhone?.let { editTextPhone.setText(it) }
        studentAddress?.let { editTextAddress.setText(it) }
        checkboxStatus.isChecked = studentChecked

        buttonCancel.setOnClickListener {
            finish()
        }

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val id = editTextId.text.toString()
            val phone = editTextPhone.text.toString()
            val address = editTextAddress.text.toString()
            val status = if (checkboxStatus.isChecked) "Checked" else "Unchecked"

            textViewSaveMessage.text = "Changes saved succesfully"
        }

        buttonDelete.setOnClickListener {
            val name = null
            val id = null
            val phone = null
            val address = null
            val status = "Unchecked"

            textViewSaveMessage.text = "Deleted succesfully"
        }
    }
}