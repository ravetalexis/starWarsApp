package com.example.starwarsapp.ui.student

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapp.R
import com.example.starwarsapp.logic.StudentEntity
import com.example.starwarsapp.logic.StudentManager
import fr.mhardy.kotlin_network.logic.ResultWrapper
import fr.mhardy.kotlin_network.utils.executeOnBackground
import fr.mhardy.kotlin_network.utils.executeOnUi
import kotlinx.android.synthetic.main.activity_search_student.*

class SearchStudentActivity : AppCompatActivity() {

    companion object {
        private val TAG = SearchStudentActivity::class.java.simpleName

        fun prepare(context: Context) = Intent(context, SearchStudentActivity::class.java)
    }

    private val studentManager by lazy { StudentManager() }
    private val studentAdapter = StudentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_student)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManagerFoundStudents = LinearLayoutManager(this)
        foundStudentsRv.apply {
            layoutManager = layoutManagerFoundStudents
            adapter = studentAdapter
            addItemDecoration(
                DividerItemDecoration(this.context, layoutManagerFoundStudents.orientation)
            )
        }

        searchField.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                search(searchField.text.toString())

                true
            }
            false
        }
    }

    private fun search(state: String) {
        executeOnBackground {
            // Retrieve students
            when (val result = studentManager.retrieveStudentsByState(state)) {
                is ResultWrapper.Success -> {
                    executeOnUi {
                        result.data?.let {
                            updateUI(it)
                        } ?: Log.w(TAG, "Unable to retrieve studentss by state")
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