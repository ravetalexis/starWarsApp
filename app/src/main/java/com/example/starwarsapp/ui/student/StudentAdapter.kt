package com.example.starwarsapp.ui.student

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.logic.StudentEntity

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.MyViewHolder>(){

    var onStudentClickListener: OnStudentClickListener? = null

    var students: List<StudentEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = students.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        students[position].let { holder.bind(it) }
    }

    inner class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val nameST: TextView = view.findViewById(R.id.nameST)
        private val genreST: TextView = view.findViewById(R.id.genreST)
        private val houseST: TextView = view.findViewById(R.id.houseST)
        private val YearOfBirthST: TextView = view.findViewById(R.id.YearOfBirthST)
        private val patronusST: TextView = view.findViewById(R.id.patronusST)

        fun bind(item: StudentEntity) {
            nameST.text = item.name
            genreST.text = item.gender
            houseST.text = item.house
            YearOfBirthST.text = item.yearOfBirth
            patronusST.text = item.patronus

            view.setOnClickListener { onStudentClickListener?.onStudentClicked(item) }
            view.setOnLongClickListener {
                onStudentClickListener?.onStudentLongClicked(item)
                true
            }
        }
    }


    interface OnStudentClickListener {
        fun onStudentClicked(student: StudentEntity)
        fun onStudentLongClicked(student: StudentEntity)
    }

}