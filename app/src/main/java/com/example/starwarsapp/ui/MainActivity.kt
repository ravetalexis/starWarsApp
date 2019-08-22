package com.example.starwarsapp.ui

//import android.support.v7.app.AppCompatActivity
//import android.support.v7.widget.DividerItemDecoration
//import android.support.v7.widget.LinearLayoutManager

import android.os.Bundle
//import android.support.annotation.MainThread
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapp.R
import com.example.starwarsapp.logic.StudentEntity
import com.example.starwarsapp.logic.StudentManager
import com.example.starwarsapp.ui.student.StudentAdapter
import fr.mhardy.kotlin_network.logic.ResultWrapper
import fr.mhardy.kotlin_network.utils.executeOnBackground
import fr.mhardy.kotlin_network.utils.executeOnUi
import kotlinx.android.synthetic.main.content_main.*



class MainActivity : AppCompatActivity() {

    private val studentManager by lazy { StudentManager() }
    private val studentAdapter = StudentAdapter()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        val layoutManagerStudents = LinearLayoutManager(this)
        studentsRv.apply {
            layoutManager = layoutManagerStudents
            adapter = studentAdapter
            addItemDecoration(
                DividerItemDecoration(this.context, layoutManagerStudents.orientation)
            )
        }

        loadData()
    }

    private fun loadData() {
        executeOnBackground {
            // Retrieve students
            when (val result = studentManager.retrieveStudents()) {
                is ResultWrapper.Success -> {
                    executeOnUi {
                        result.data?.let {
                            updateUI(it)
                        } ?: Log.w(TAG, "Unable to retrieve students")
                    }
                }
                is ResultWrapper.Error -> {
                    Log.e(TAG, result.throwable.message)
                }
            }
        }
    }

    @MainThread
    private fun updateUI(students: List<StudentEntity>) {
        studentAdapter.students = students
    }
}
