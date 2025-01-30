package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {
    private var originalStudentId: String = ""  // Keep track of the original ID

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

        originalStudentId = intent.getStringExtra("studentId") ?: ""
        val studentName = intent.getStringExtra("studentName")
        val studentPhone = intent.getStringExtra("studentPhone")
        val studentAddress = intent.getStringExtra("studentAddress")
        val studentChecked = intent.getBooleanExtra("isChecked", false)

        studentName?.let { editTextName.setText(it) }
        editTextId.setText(originalStudentId)
        studentPhone?.let { editTextPhone.setText(it) }
        studentAddress?.let { editTextAddress.setText(it) }
        checkboxStatus.isChecked = studentChecked

        buttonCancel.setOnClickListener {
            finish()
        }

        buttonSave.setOnClickListener {
            if (originalStudentId.isNotEmpty()) {
                val updatedStudent = Student(
                    name = editTextName.text.toString(),
                    id = editTextId.text.toString(),
                    phone = editTextPhone.text.toString(),
                    address = editTextAddress.text.toString(),
                    isChecked = checkboxStatus.isChecked
                )
                
                Model.instance.updateStudentById(originalStudentId, updatedStudent)
                textViewSaveMessage.text = "Changes saved successfully"
                
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }, 1000)
            }
        }

        buttonDelete.setOnClickListener {
            if (originalStudentId.isNotEmpty()) {
                Model.instance.deleteStudentById(originalStudentId)
                textViewSaveMessage.text = "Student deleted successfully"
                
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }, 1000)
            }
        }
    }
}