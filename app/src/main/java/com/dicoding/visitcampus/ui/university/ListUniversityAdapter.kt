package com.dicoding.visitcampus.ui.university


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.University
import com.dicoding.visitcampus.databinding.ItemUniversityBinding

class ListUniversityAdapter: ListAdapter<University, ListUniversityAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(val binding: ItemUniversityBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(university: University){
            binding.imgItemPhoto.setImageResource(university.logoPhoto)
            binding.tvItemName.text = university.univName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val university = getItem(position)
        holder.bind(university)

    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<University>() {
            override fun areItemsTheSame(oldItem: University, newItem: University): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: University, newItem: University): Boolean {
                return oldItem == newItem
            }
        }
    }
}


