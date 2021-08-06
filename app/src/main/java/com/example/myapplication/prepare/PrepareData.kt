package com.example.myapplication.prepare

import com.example.myapplication.extension.concat
import com.example.myapplication.model.Student
import com.example.myapplication.other.Const.BY_NAME
import com.example.myapplication.other.Const.BY_PHONE_NUMBER
import com.example.myapplication.other.Const.BY_SPECIALIZED
import com.example.myapplication.other.Const.BY_UNIVERSITY
import com.example.myapplication.other.Const.BY_YEAR

object PrepareData {
    var listStudents: MutableList<Student> = mutableListOf(
        Student("Le", 1995, "099999999", "CNTT", false),
        Student("Hong", 1998, "077777777", "BMTT", true),
        Student("Su", 1998, "066666666", "CNTT", true),
        Student("Duc", 1999, "055555555", "CNTT", false),
        Student("Nguyen", 1991, "088888888", "ATTT", true),
        Student("Van", 1993, "022222222", "ATTT", true),
        Student("Thi", 1990, "044444444", "BMMT", false),
        Student("Ti", 2000, "011111111", "CNTT", true)

    )

    lateinit var resultList : MutableList<Student>

    fun search(keyword: String){
        resultList = listStudents.filter { student->
            (student.concat().lowercase().contains(keyword.lowercase()))
        }.toMutableList()
    }

    private fun phoneNumberExist(student: Student): Boolean {
        listStudents.forEach {
            if (it.phoneNumber.equals(student.phoneNumber)) return true
        }
        return false
    }

    fun addStudent(student: Student): Boolean {
        if (!phoneNumberExist(student)) {
            listStudents.add(student)
            return true
        } else return false
    }

    fun editStudent(oldStudent: Student, newStudent: Student): Boolean {
        if (!phoneNumberExist(newStudent) ||
            (phoneNumberExist(newStudent) && newStudent.phoneNumber.equals(oldStudent.phoneNumber))
        ) {
            listStudents.remove(oldStudent)
            listStudents.add(newStudent)
            return true
        } else return false
    }

    fun sort(options: String) {
        when (options) {
            BY_NAME -> listStudents.sortBy {
                it.name
            }
            BY_PHONE_NUMBER -> listStudents.sortBy {
                Integer.parseInt(it.phoneNumber)
            }
            BY_YEAR -> listStudents.sortBy {
                (it.year)
            }
            BY_SPECIALIZED -> listStudents.sortBy {
                (it.specialized)
            }
            BY_UNIVERSITY -> listStudents.sortBy {
                (it.isUniversity)
            }
            else -> Unit
        }
    }


}