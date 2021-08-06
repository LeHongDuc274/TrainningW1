package com.example.myapplication.fragment

import android.app.Application
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.StudentAdapter
import com.example.myapplication.model.Student
import com.example.myapplication.other.Const.BY_NAME
import com.example.myapplication.other.Const.BY_PHONE_NUMBER
import com.example.myapplication.other.Const.BY_SPECIALIZED
import com.example.myapplication.other.Const.BY_UNIVERSITY
import com.example.myapplication.other.Const.BY_YEAR
import com.example.myapplication.prepare.PrepareData
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var navController: NavController
    private lateinit var studentAdapter: StudentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentAdapter = StudentAdapter(onDelete, onEdit)
        studentAdapter.setStudents(PrepareData.listStudents)

        setupRecycleView()

        navController = findNavController()

        btn_add.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_addFragment)
        }

        btn_search.setOnClickListener {
            var keyword : String = edt_search.text.trim().toString()
            PrepareData.search(keyword)
            studentAdapter.apply {
                setStudents(PrepareData.resultList)
                notifyDataSetChanged()
            }
        }

        btn_cancel_search.setOnClickListener {
            studentAdapter.apply {
                setStudents(PrepareData.listStudents)
                notifyDataSetChanged()
                edt_search.text.clear()
            }
        }

        sort_menu.setOnClickListener {
            showPopup(it)
        }

    }

    private fun setupRecycleView() {
        recycle_view.apply {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private val onDelete: (Student) -> Unit = {
        Toast.makeText(activity, it.phoneNumber, Toast.LENGTH_LONG).show()
        PrepareData.listStudents.remove(it)
        studentAdapter.notifyDataSetChanged()
    }

    private val onEdit: (Student) -> Unit = {
        var bundle = bundleOf(
            "studentArgs" to it
        )
        navController.navigate(R.id.action_homeFragment_to_editFragment, bundle)
        Toast.makeText(activity, it.phoneNumber, Toast.LENGTH_LONG).show()
    }

    private fun showPopup(view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.inflate(R.menu.header_popup_menu)

        popup.setOnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.name -> {
                    PrepareData.sort(BY_NAME)
                }
                R.id.year -> {
                    PrepareData.sort(BY_YEAR)
                }
                R.id.phone -> {
                    PrepareData.sort(BY_PHONE_NUMBER)
                }
                R.id.specicalized -> {
                    PrepareData.sort(BY_SPECIALIZED)
                }
                R.id.university -> {
                    PrepareData.sort(BY_UNIVERSITY)
                }
                else -> Unit
            }
            studentAdapter.setStudents(PrepareData.listStudents)
            studentAdapter.notifyDataSetChanged ()
            true
        }
        popup.show()
    }
}