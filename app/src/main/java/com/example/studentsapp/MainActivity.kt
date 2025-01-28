package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupAddStudentButton()
    }

    private fun setupUI() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupWindowInsets()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupAddStudentButton() {
        findViewById<MaterialButton>(R.id.main_activity_add_student_button).setOnClickListener {
            navigateToAddStudent()
        }
    }

    private fun navigateToAddStudent() {
        startActivity(Intent(this, AddStudentActivity::class.java))
    }
}