package com.example.starwarsapp.ui.student


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.logic.StudentEntity
import kotlinx.android.synthetic.main.student_item.view.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.net.Uri
import android.os.ParcelFileDescriptor
import com.example.starwarsapp.core.extension.age
import com.example.starwarsapp.core.extension.age2
import com.squareup.picasso.Picasso
import java.io.IOException


class StudentAdapter(val context: Context) : RecyclerView.Adapter<StudentAdapter.MyViewHolder>(){

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
        //val imgResId = R.drawable.ic_launcher_background
        private val imageST: ImageView = view.findViewById(R.id.imageView)

        fun bind(item: StudentEntity) {
            nameST.text = item.name
            genreST.text = item.gender
            houseST.text = item.house
            YearOfBirthST.text = item.yearOfBirth.age2().toString()
            patronusST.text = item.patronus
            //imageST.setImageURI(item.image)
            //imageST.setImageBitmap(getBitmapFromUri(item.image, context))
            Picasso.get().load(item.image).into(imageST)

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

    @Throws(IOException::class)
    private fun getBitmapFromUri(uri: Uri, context: Context): Bitmap {
        val parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor.getFileDescriptor()
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
    }

}