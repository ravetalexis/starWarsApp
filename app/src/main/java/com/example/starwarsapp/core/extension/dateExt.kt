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

        return age.toString()
    }
}

fun String.age2(): Int {


        val dateCourant = Date()
        val anneeCourant =  dateCourant.year + 1900
        val age = (anneeCourant - Integer.parseInt(this))

        return age

}