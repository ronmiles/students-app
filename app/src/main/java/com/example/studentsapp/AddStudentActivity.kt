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

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        val checkedButton: CheckBox = findViewById(R.id.add_student_activity_checked_button)
        val saveButton: Button = findViewById(R.id.add_student_activity_save_button)
        val cancelButton: Button = findViewById(R.id.add_student_activity_cancel_button)
        val nameEditText: EditText = findViewById(R.id.add_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.add_student_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.add_student_activity_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.add_student_activity_address_edit_text)
        val saveMessageTextView: TextView = findViewById(R.id.add_student_activity_save_message_text_view)

        saveButton.setOnClickListener {
            val newStudent = Student(
                name = nameEditText.text.toString(),
                id = idEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                address = addressEditText.text.toString(),
                isChecked = checkedButton.isChecked
            )
            
            Model.instance.addStudent(newStudent)
            saveMessageTextView.text = "Student added successfully"
            
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }, 1000)
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}