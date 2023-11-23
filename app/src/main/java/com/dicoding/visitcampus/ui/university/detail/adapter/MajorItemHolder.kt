package com.dicoding.visitcampus.ui.university.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.Major
import com.dicoding.visitcampus.databinding.ItemFacultyBinding
import com.dicoding.visitcampus.databinding.ItemMajorBinding


class MajorItemHolder(val binding: ItemMajorBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindContent(major: Major){
        binding.apply {
            tvMajorName.text = major.majorName
            tvMajorAccreditation.text = major.majorAccreditation
        }
    }
}