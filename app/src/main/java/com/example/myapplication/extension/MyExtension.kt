package com.example.myapplication.extension

import android.text.Editable
import com.example.myapplication.model.Student

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
fun Student.concat(): String =
    "${this.name} + ${this.phoneNumber}+ ${this.year}+ ${this.specialized}+ ${this.isUniversity}"