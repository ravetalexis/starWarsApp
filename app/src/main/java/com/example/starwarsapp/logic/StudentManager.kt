package com.example.starwarsapp.logic

import android.util.Log
import fr.mhardy.kotlin_network.core.rest.http.HttpResponse

class StudentManager {
    private val restManager by lazy { SimpleRestManager() }

    companion object {
        const val TAG = "StudentManager"
    }

    fun retrieveBreweries(): ResultWrapper<List<StudentEntity>> {
        Log.d(TAG, "Retrieving breweries")
        return when (val responseDTO = restManager.retrieveStudents()) {
            is HttpResponse.Success -> ResultWrapper.Success(responseDTO.value?.map { it.toEntity() })
            is HttpResponse.Error -> ResultWrapper.Error(responseDTO.throwable)
        }
    }

    /*fun retrieveBreweriesByState(state: String): ResultWrapper<List<BreweryEntity>> {
        Log.d(TAG, "Retrieving breweries by state $state")
        return when (val responseDTO = restManager.retrieveBreweriesByState(state)) {
            is HttpResponse.Success -> ResultWrapper.Success(responseDTO.value?.map { it.toEntity() })
            is HttpResponse.Error -> ResultWrapper.Error(responseDTO.throwable)
        }
    }*/
}