package com.dicoding.visitcampus.ui.university.detail.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.model.Faculty
import com.dicoding.visitcampus.data.model.Major
import com.dicoding.visitcampus.databinding.ItemFacultyBinding
import com.dicoding.visitcampus.databinding.ItemMajorBinding

class FacultyMajorAdapter(private val data: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val ITEM_FACULTY = 0
        private const val ITEM_MAJOR = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is String -> ITEM_FACULTY
            is Major -> ITEM_MAJOR
            else -> throw IllegalArgumentException(data[position].toString())

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_FACULTY -> FacultyHeaderHolder(binding = ItemFacultyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            ITEM_MAJOR -> MajorItemHolder(binding = ItemMajorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_FACULTY -> {
                val headerHolder = holder as FacultyHeaderHolder
                headerHolder.bindContent(data[position] as String)
            }
            ITEM_MAJOR -> {
                val itemHolder = holder as MajorItemHolder
                itemHolder.bindContent(data[position] as Major)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }


}