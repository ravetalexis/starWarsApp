package com.example.starwarsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.starwarsapp.R
import com.example.starwarsapp.logic.StudentEntity
import com.example.starwarsapp.logic.StudentManager
import com.example.starwarsapp.ui.student.StudentAdapter
import com.example.starwarsapp.logic.ResultWrapper
import com.example.starwarsapp.utils.executeOnBackground
import com.example.starwarsapp.utils.executeOnUi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val studentManager by lazy { StudentManager() }
    private val studentAdapter = StudentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManagerStudent = LinearLayoutManager(this)
            studentsRv.apply {
                layoutManager = layoutManagerStudent
                adapter = studentAdapter
                addItemDecoration(
                    DividerItemDecoration(this.context, layoutManagerStudent.orientation)
                )
            }

        loadData()


    }

    private fun loadData() {
        executeOnBackground {
            // Retrieve breweries
            when (val result = studentManager.retrieveBreweries()) {
                is ResultWrapper.Success -> {
                    executeOnUi {
                        result.data?.let {
                            updateUI(it)
                        } ?: Log.w("DonnéeRécupéré", "Unable to retrieve breweries")
                    }
                }
                is ResultWrapper.Error -> {
                    Log.e("FailDonnée", result.throwable.message)
                }
            }
        }
    }

    @MainThread
    private fun updateUI(breweries: List<StudentEntity>) {
        studentAdapter.students = breweries
    }
}
