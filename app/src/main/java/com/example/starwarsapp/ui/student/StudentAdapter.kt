package com.example.starwarsapp.ui.student


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.logic.StudentEntity

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.MyViewHolder>(){

    var students: List<StudentEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount() = students.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.brewery_item, parent, false))
    }

}