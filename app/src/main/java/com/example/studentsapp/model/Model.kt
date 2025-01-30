package com.example.studentsapp.model

class Model private constructor() {
    private val _students: MutableList<Student> = mutableListOf()
    val students: List<Student> get() = _students.toList()

    companion object {
        val instance: Model by lazy { Model() }
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
        _students.add(student)
    }

    fun getStudentById(id: String): Student? {
        return _students.find { it.id == id }
    }

    fun updateStudentById(updatedStudent: Student) {
        val index = _students.indexOfFirst { it.id == updatedStudent.id }
        if (index != -1) {
            _students[index] = updatedStudent
        }
    }

    fun deleteStudentById(id: String) {
        val index = _students.indexOfFirst { it.id == id }
        if (index != -1) {
            _students.removeAt(index)
        }
    }
}
