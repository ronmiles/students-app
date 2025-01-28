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
        repeat(20) { index ->
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

    fun updateStudent(student: Student, position: Int) {
        _students[position] = student
    }

    fun deleteStudent(position: Int) {
        _students.removeAt(position)
    }
}
