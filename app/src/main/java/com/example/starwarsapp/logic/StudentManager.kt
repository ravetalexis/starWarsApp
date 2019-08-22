package com.example.starwarsapp.logic

import android.util.Log
import fr.mhardy.kotlin_network.core.rest.http.HttpResponse
import fr.mhardy.kotlin_network.logic.ResultWrapper
import fr.mhardy.kotlin_network.logic.SimpleRestManager

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

    fun retrieveStudentsByState(state: String): ResultWrapper<List<StudentEntity>> {
        Log.d(TAG, "Retrieving students by state $state")
        return when (val responseDTO = restManager.retrieveStudentsByState(state)) {
            is HttpResponse.Success -> ResultWrapper.Success(responseDTO.value?.map { it.toEntity() })
            is HttpResponse.Error -> ResultWrapper.Error(responseDTO.throwable)
        }
    }
}