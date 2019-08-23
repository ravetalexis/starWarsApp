package com.example.starwarsapp.core.extension

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


fun String.age(): String {

        String.let {

        val dateCourant = Date()
        val anneeCourant =  dateCourant.year
        val age = (anneeCourant - Integer.parseInt(this))
        val ageString = age.toString()

         return ageString
        }
}