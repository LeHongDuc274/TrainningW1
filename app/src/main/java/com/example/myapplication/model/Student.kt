package com.example.myapplication.model

import java.io.Serializable

data class Student(
    var name: String,
    var year: Int,
    var phoneNumber: String,
    var specialized: String,
    var isUniversity: Boolean
) : Serializable