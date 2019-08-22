package com.example.starwarsapp.ui.student

import android.support.v7.widget.RecyclerView
import com.example.starwarsapp.logic.StudentEntity

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.MyViewHolder>(){

    var students: List<StudentEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

}