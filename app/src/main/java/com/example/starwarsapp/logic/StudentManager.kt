package com.example.starwarsapp.logic

import android.util.Log
import fr.mhardy.kotlin_network.core.rest.http.HttpResponse
import fr.mhardy.kotlin_network.logic.ResultWrapper

class StudentManager {
    private val restManager by lazy { SimpleRestManager() }

    companion object {
        const val TAG = "StudentManager"
    }

    fun retrieveStudents(): ResultWrapper<List<StudentEntity>> {
        Log.d(TAG, "Retrieving students")
        return when (val responseDTO = restManager.retrieveStudents()) {
            is HttpResponse.Success -> ResultWrapper.Success(responseDTO.value?.map { it.toEntity() })
            is HttpResponse.Error -> ResultWrapper.Error(responseDTO.throwable)
        }
    }

    fun retrieveStudentsByName(name: String): ResultWrapper<List<StudentEntity>> {
        Log.d(TAG, "Retrieving students by state $name")
        return when (val responseDTO = restManager.retrieveStudentsByName(name)) {
            is HttpResponse.Success -> ResultWrapper.Success(responseDTO.value?.map { it.toEntity() })
            is HttpResponse.Error -> ResultWrapper.Error(responseDTO.throwable)
        }
    }

}