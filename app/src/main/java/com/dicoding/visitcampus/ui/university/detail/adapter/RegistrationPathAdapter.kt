package com.dicoding.visitcampus.ui.university.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.AlumnusProfile
import com.dicoding.visitcampus.data.model.RegistrationPath
import com.dicoding.visitcampus.databinding.ItemAlumnusProfileBinding
import com.dicoding.visitcampus.databinding.ItemRegistrationPathBinding

class RegistrationPathAdapter: ListAdapter<RegistrationPath, RegistrationPathAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(val binding: ItemRegistrationPathBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(path: RegistrationPath){
            binding.apply {
                tvPathName.text = path.pathName
                tvPathDescription.text = path.pathDescription
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRegistrationPathBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val achievement = getItem(position)
        holder.bind(achievement)
    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RegistrationPath>() {
            override fun areItemsTheSame(oldItem: RegistrationPath, newItem: RegistrationPath): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: RegistrationPath, newItem: RegistrationPath): Boolean {
                return oldItem == newItem
            }
        }
    }
}


