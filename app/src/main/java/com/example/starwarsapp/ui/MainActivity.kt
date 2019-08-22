package com.example.starwarsapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.MainThread
import android.util.Log
import com.example.starwarsapp.R
import com.example.starwarsapp.logic.StudentEntity
import com.example.starwarsapp.logic.StudentManager
import com.example.starwarsapp.ui.student.StudentAdapter
import fr.mhardy.kotlin_network.logic.ResultWrapper
import fr.mhardy.kotlin_network.utils.executeOnBackground
import fr.mhardy.kotlin_network.utils.executeOnUi

class MainActivity : AppCompatActivity() {

    private val studentManager by lazy { StudentManager() }
    private val studentAdapter = StudentAdapter()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    private fun loadData() {
        executeOnBackground {
            // Retrieve breweries
            when (val result = studentManager.retrieveBreweries()) {
                is ResultWrapper.Success -> {
                    executeOnUi {
                        result.data?.let {
                            updateUI(it)
                        } ?: Log.w(TAG, "Unable to retrieve breweries")
                    }
                }
                is ResultWrapper.Error -> {
                    Log.e(TAG, result.throwable.message)
                }
            }
        }
    }

    @MainThread
    private fun updateUI(breweries: List<StudentEntity>) {
        studentAdapter.students = breweries
    }
}
