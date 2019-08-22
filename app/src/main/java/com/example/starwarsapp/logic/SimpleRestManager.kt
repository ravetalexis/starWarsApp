package com.example.starwarsapp.logic

import android.util.Log
import com.example.starwarsapp.core.rest.dto.StudentsDTO
import fr.mhardy.kotlin_network.core.rest.http.HttpClient
import fr.mhardy.kotlin_network.core.rest.http.HttpMethod.GET
import fr.mhardy.kotlin_network.core.rest.http.HttpRequest
import fr.mhardy.kotlin_network.core.rest.http.HttpResponse
import fr.mhardy.kotlin_network.utils.dataArrayFromJson
import fr.mhardy.kotlin_network.utils.jsonToList
import java.net.URL

class SimpleRestManager {

    companion object {
        const val TAG: String = "RestManager"
    }

    private val httpClient by lazy { HttpClient() }
    private val rootUrl = "http://hp-api.herokuapp.com/api"

    // STUDENTS
    fun retrieveStudents(): HttpResponse<List<StudentsDTO>> {
        val response = httpClient.executeRequest<List<StudentsDTO>>(
            HttpRequest(
                GET,
                URL("$rootUrl/characters/students")
            )
        )
        if (response is HttpResponse.Success) {
            val data = dataArrayFromJson(response.body)
            data?.let {
                response.value = jsonToList(it)?.map { student -> StudentsDTO.fromJson(student) }
            } ?: Log.w(TAG, "Unable to parse data in json")
        }
        return response
    }

    fun retrieveStudentsByName(name: String): HttpResponse<List<StudentsDTO>> {
        val formattedName = name.replace(" ", "_")
        val response = httpClient.executeRequest<List<StudentsDTO>>(
            HttpRequest(
                GET,
                URL("$rootUrl/characters/students?by_name=$formattedName")
            )
        )
        if (response is HttpResponse.Success) {
            val data = dataArrayFromJson(response.body)
            data?.let {
                response.value = jsonToList(it)?.map { student -> StudentsDTO.fromJson(student) }
            } ?: Log.w(TAG, "Unable to parse data in json")
        }
        return response
    }
}