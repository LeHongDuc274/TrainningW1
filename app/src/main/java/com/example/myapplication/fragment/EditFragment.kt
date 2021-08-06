package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.extension.toEditable
import com.example.myapplication.model.Student
import com.example.myapplication.prepare.PrepareData

import kotlinx.android.synthetic.main.fragment_edit.*


class EditFragment : Fragment(R.layout.fragment_edit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val arg = arguments
        val studentArg = arguments?.get("studentArgs") as Student

        edt_name_edit.text = (studentArg.name).toEditable()
        edt_phone_number_edit.text = (studentArg.phoneNumber).toEditable()
        edt_specialized_edit.text = (studentArg.specialized).toEditable()
        cb_university.isChecked = studentArg.isUniversity
        cb_college.isChecked = !studentArg.isUniversity

        edt_year_edit.text = (studentArg.year.toString()).toEditable()

        cb_university.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cb_college.isChecked = false
            }
        }
        cb_college.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cb_university.isChecked = false
            }
        }

        btn_save_edit.setOnClickListener {
            if (checkEmptyEdt()
            ) {
                Toast.makeText(activity, "khong bo trong cac muc", Toast.LENGTH_LONG).show()
            } else {

                val newstudent = edtToOj()
                val edited = PrepareData.editStudent(studentArg, newstudent)
                if (edited) {
                    val navController = findNavController()
                    navController.popBackStack()
                    Toast.makeText(activity, "edit complete", Toast.LENGTH_SHORT).show()
                    //  navController.navigate(R.id.action_addFragment_to_homeFragment)
                } else {
                    Toast.makeText(activity, "phoneNumber is exist", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkEmptyEdt(): Boolean {
        if (edt_name_edit.text.isEmpty() || edt_phone_number_edit.text.isEmpty() ||
                edt_specialized_edit.text.isEmpty() || (!cb_university.isChecked && !cb_college.isChecked) || edt_year_edit.text.isEmpty()
        ) return true
        return false
    }

    private fun edtToOj(): Student {
        val name = edt_name_edit.text.trim().toString()
        val phone = edt_phone_number_edit.text.trim().toString()
        val specialized = edt_specialized_edit.text.trim().toString()
       // val year = edt_year_edit.text.trim().toString()
        val y = Integer.parseInt(edt_year_edit.text.trim().toString())

        //val year = yearStr.substring(0,yearStr.length-1).toInt()
        val isUniversity = cb_university.isChecked
        return Student(name, y, phone, specialized, isUniversity)
    }
}