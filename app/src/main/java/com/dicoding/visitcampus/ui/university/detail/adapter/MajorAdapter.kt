package com.dicoding.visitcampus.ui.university.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.Faculty
import com.dicoding.visitcampus.data.model.Major
import com.dicoding.visitcampus.data.response.MajorItem
import com.dicoding.visitcampus.databinding.ItemFacultyBinding
import com.dicoding.visitcampus.databinding.ItemMajorBinding

class MajorAdapter: ListAdapter<MajorItem, MajorAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(val binding: ItemMajorBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(major: MajorItem){
            binding.apply {
                tvMajorName.text = major.majorName
                tvMajorAccreditation.text = major.majorAccreditation
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemMajorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val achievement = getItem(position)
        holder.bind(achievement)
    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MajorItem>() {
            override fun areItemsTheSame(oldItem: MajorItem, newItem: MajorItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: MajorItem, newItem: MajorItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}