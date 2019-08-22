package com.example.starwarsapp.core.rest.dto

import android.net.Uri
import com.example.starwarsapp.logic.StudentEntity
import org.json.JSONObject
import java.util.ArrayList

data class StudentsDTO(
    val name: String,
    val gender: String,
    val house: String,
    val yearOfBirth: String,
    val patronus: String,
    val image: String
) {
    companion object {
        /**
         * Returns an BreweryDTO from a [JSONObject].
         */
        fun fromJson(jsonObject: JSONObject): StudentsDTO =
            StudentsDTO(
                name = jsonObject.getString("name"),
                gender = jsonObject.getString("brewery_type"),
                house = jsonObject.getString("street"),
                yearOfBirth = jsonObject.getString("city"),
                patronus = jsonObject.getString("state"),
                image = jsonObject.getString("postal_code")
            )
    }

    /**
     * Transforms a DTO into an [StudentEntity].
     */
    fun toEntity(): StudentEntity {
        return StudentEntity(
            name,
            gender,
            house,
            yearOfBirth,
            patronus,
            image
        )
    }

}