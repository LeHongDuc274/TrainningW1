package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Student
import kotlinx.android.synthetic.main.layout_item.view.*

class StudentAdapter(
    private val onDelete: (Student) -> Unit,
    private val onEdit: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var students: MutableList<Student> = mutableListOf()

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.itemView.apply {
            tv_name.text = student.name
            tv_phone_number.text = student.phoneNumber
            tv_year.text = student.year.toString()
            tv_specialized.text = student.specialized
            tv_university_or_college.text = if (student.isUniversity) "University" else "College"

            btn_delete.setOnClickListener {
                onDelete(student)
            }
            btn_edit.setOnClickListener {
                onEdit(student)
            }
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }

    fun setStudents(students: MutableList<Student>){
            this.students = students
            notifyDataSetChanged()
    }
}