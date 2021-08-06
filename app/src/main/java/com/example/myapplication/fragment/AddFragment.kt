package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.model.Student
import com.example.myapplication.prepare.PrepareData
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment(R.layout.fragment_add) {
    private lateinit var student: Student


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cb_university_add.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cb_college_add.isChecked = false
            }
        }
        cb_college_add.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cb_university_add.isChecked = false
            }
        }
        btn_save_add.setOnClickListener {
            if (checkEmptyEdt()) {
                Toast.makeText(activity, "khong bo trong cac muc", Toast.LENGTH_LONG).show()
            } else {
                student = edtToOj()
                val added = PrepareData.addStudent(student)
                if (added) {
                    val navController = findNavController()
                    navController.popBackStack()
                    Toast.makeText(activity, "add complete", Toast.LENGTH_SHORT).show()
                    //  navController.navigate(R.id.action_addFragment_to_homeFragment)
                } else {
                    Toast.makeText(activity, "phoneNumber is exist", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun checkEmptyEdt(): Boolean {
        if (edt_name.text.isEmpty() || edt_phone_number.text.isEmpty() ||
            edt_specialized.text.isEmpty() || (!cb_university_add.isChecked && !cb_college_add.isChecked) || edt_year.text.isEmpty()
        ) return true
        return false
    }

    private fun edtToOj(): Student {
        val name = edt_name.text.trim().toString()
        val phone = edt_phone_number.text.trim().toString()
        val specialized = edt_specialized.text.trim().toString()
        // val year = edt_year_edit.text.trim().toString()
        val y = Integer.parseInt(edt_year.text.trim().toString())

        //val year = yearStr.substring(0,yearStr.length-1).toInt()
        val isUniversity = cb_university_add.isChecked
        return Student(name, y, phone, specialized, isUniversity)
    }
}