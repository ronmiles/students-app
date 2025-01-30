package com.example.studentsapp.model

import android.util.Log

class Model private constructor() {
    private val _students: MutableList<Student> = mutableListOf()
    val students: List<Student> get() = _students.toList()

    companion object {
        val instance: Model by lazy { Model() }
        private const val TAG = "Model"
    }

    init {
        initData()
    }

    private fun initData() {
        repeat(4) { index ->
            addStudent(
                Student(
                    name = "Student ${index + 1}",
                    id = "ID${index + 1}",
                    phone = "+972-${500000000 + index}",
                    address = "Address ${index + 1}",
                    avatarUrl = "",
                    isChecked = false
                )
            )
        }
    }

    fun addStudent(student: Student) {
        Log.d(TAG, "Adding student: $student")
        _students.add(student)
        logStudents()
    }

    fun getStudentById(id: String): Student? {
        val student = _students.find { it.id == id }
        Log.d(TAG, "Getting student by ID: $id, Found: $student")
        return student
    }

    fun updateStudentById(originalId: String, updatedStudent: Student) {
        Log.d(TAG, "Updating student with original ID: $originalId to: $updatedStudent")
        val index = _students.indexOfFirst { it.id == originalId }
        if (index != -1) {
            _students[index] = updatedStudent
            Log.d(TAG, "Student updated successfully at index: $index")
        } else {
            Log.d(TAG, "Student not found for update")
        }
        logStudents()
    }

    fun deleteStudentById(id: String) {
        Log.d(TAG, "Deleting student with ID: $id")
        val index = _students.indexOfFirst { it.id == id }
        if (index != -1) {
            _students.removeAt(index)
            Log.d(TAG, "Student deleted successfully")
        } else {
            Log.d(TAG, "Student not found for deletion")
        }
        logStudents()
    }

    private fun logStudents() {
        Log.d(TAG, "Current students list:")
        _students.forEachIndexed { index, student ->
            Log.d(TAG, "$index: $student")
        }
    }
}
