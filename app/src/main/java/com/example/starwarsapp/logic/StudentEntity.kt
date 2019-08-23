package com.example.starwarsapp.logic

import android.net.Uri
import java.util.ArrayList

data class StudentEntity(
    val name: String,
    val gender: String,
    val house: String,
    val yearOfBirth: String,
    val patronus: String,
    val image: Uri
) {

}