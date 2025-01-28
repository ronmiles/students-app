package com.example.studentsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox
import android.widget.FrameLayout

class StudentListViewActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: StudentsAdapter
    private val students: List<Student> = Model.instance.students

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupListView()
    }

    private fun setupUI() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_list_view)
        setupWindowInsets()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupListView() {
        listView = findViewById(R.id.student_list_view)
        adapter = StudentsAdapter()
        listView.adapter = adapter
    }

    inner class StudentsAdapter : BaseAdapter() {
        override fun getCount(): Int = students.size

        override fun getItem(position: Int): Student = students[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(parent.context)
                .inflate(R.layout.student_list_row, parent, false)

            val student = getItem(position)
            
            view.apply {
                findViewById<TextView>(R.id.student_row_name_text_view).text = student.name
                findViewById<TextView>(R.id.student_row_id_text_view).text = student.id
                
                val checkbox = findViewById<MaterialCheckBox>(R.id.student_row_check_box)
                checkbox.isChecked = student.isChecked
                
                // Handle the container click instead of the checkbox directly
                findViewById<FrameLayout>(R.id.checkbox_container).setOnClickListener {
                    student.isChecked = !student.isChecked
                    checkbox.isChecked = student.isChecked
                }
            }

            return view
        }
    }
}