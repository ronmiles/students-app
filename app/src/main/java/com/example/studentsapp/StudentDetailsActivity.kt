package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.google.android.material.appbar.MaterialToolbar

class StudentDetailsActivity : AppCompatActivity() {
    private var studentId: String = ""
    private lateinit var textViewName: TextView
    private lateinit var textViewId: TextView
    private lateinit var textViewPhone: TextView
    private lateinit var textViewAddress: TextView
    private lateinit var checkboxStatus: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupToolbar()

        checkboxStatus = findViewById(R.id.student_details_activity_checked_button)
        textViewName = findViewById(R.id.student_details_activity_name_text)
        textViewId = findViewById(R.id.student_details_activity_id_text)
        textViewPhone = findViewById(R.id.student_details_activity_phone_text)
        textViewAddress = findViewById(R.id.student_details_activity_address_text)
        val buttonEdit: Button = findViewById(R.id.student_details_activity_edit_button)

        studentId = intent.getStringExtra("studentId") ?: ""
        
        loadStudentData()

        checkboxStatus.setOnClickListener {
            val student = Model.instance.getStudentById(studentId)
            student?.let {
                val updatedStudent = it.copy(isChecked = checkboxStatus.isChecked)
                Model.instance.updateStudentById(updatedStudent)
            }
        }

        buttonEdit.setOnClickListener {
            val student = Model.instance.getStudentById(studentId)
            val intent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("studentId", studentId)
                putExtra("studentName", student?.name)
                putExtra("studentPhone", student?.phone)
                putExtra("studentAddress", student?.address)
                putExtra("isChecked", student?.isChecked)
            }
            startActivity(intent)
        }
    }

    private fun loadStudentData() {
        val student = Model.instance.getStudentById(studentId)
        student?.let {
            textViewName.text = it.name
            textViewId.text = it.id
            textViewPhone.text = it.phone
            textViewAddress.text = it.address
            checkboxStatus.isChecked = it.isChecked
        }
    }

    override fun onResume() {
        super.onResume()
        loadStudentData() // Refresh the data when returning to this screen
    }

    private fun setupUI() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        setupWindowInsets()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)
        
        // Handle toolbar click for back navigation
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        
        // Make the entire toolbar clickable for back navigation
        toolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Optional: Override onBackPressed if you need custom behavior
    override fun onBackPressed() {
        super.onBackPressed()
        // Add any custom behavior here if needed
    }
}