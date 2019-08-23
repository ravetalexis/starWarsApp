package com.example.starwarsapp.core.extension

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun String.age(): String {

    String.let {

        val dateCourant = LocalDate.now()
        val anneeCourant =  dateCourant.year
        val age = (anneeCourant - Integer.parseInt(this))

        return age.toString()
    }
}

/*fun String.age(): Int? {

    String.let {

        val dateCourant = LocalDate.now()
        val anneeCourant =  dateCourant.year
        val age = (anneeCourant - Integer.parseInt(this))

        return age
    }
}*/