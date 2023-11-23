package com.dicoding.visitcampus.ui.university.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.databinding.ItemFacultyBinding
import com.dicoding.visitcampus.databinding.ItemRegistrationPathBinding

class FacultyHeaderHolder(val binding: ItemFacultyBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindContent(text: String){
        binding.apply {
            tvFacultyName.text = text
        }
    }


}


