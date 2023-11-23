package com.dicoding.visitcampus.ui.university.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.AlumnusProfile
import com.dicoding.visitcampus.databinding.ItemAlumnusProfileBinding

class AlumnusProfileAdapter: ListAdapter<AlumnusProfile, AlumnusProfileAdapter.ListViewHolder>(
    DIFF_CALLBACK
) {
    class ListViewHolder(val binding: ItemAlumnusProfileBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(alumnus: AlumnusProfile){
            binding.apply {
                tvAlumnusName.text = alumnus.alumnusName
                tvAlumnusCohort.text = alumnus.alumnusCohort
                tvAlumnusCareer.text = alumnus.alumnusCareer
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemAlumnusProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val achievement = getItem(position)
        holder.bind(achievement)
    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AlumnusProfile>() {
            override fun areItemsTheSame(oldItem: AlumnusProfile, newItem: AlumnusProfile): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: AlumnusProfile, newItem: AlumnusProfile): Boolean {
                return oldItem == newItem
            }
        }
    }
}



