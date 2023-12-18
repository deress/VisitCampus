package com.dicoding.visitcampus.ui.university.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.visitcampus.data.model.AlumnusProfile
import com.dicoding.visitcampus.data.response.AlumnusProfileItem
import com.dicoding.visitcampus.databinding.ItemAlumnusProfileBinding

class AlumnusProfileAdapter: ListAdapter<AlumnusProfileItem, AlumnusProfileAdapter.ListViewHolder>(
    DIFF_CALLBACK
) {
    class ListViewHolder(val binding: ItemAlumnusProfileBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(alumnus: AlumnusProfileItem){
            binding.apply {
                val context = itemView.context
                val logoResId = context.resources.getIdentifier(alumnus.alumnusPhoto, "drawable", context.packageName)

                Glide.with(itemView.context)
                    .load(logoResId)
                    .into(ivAlumnusPhoto)
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AlumnusProfileItem>() {
            override fun areItemsTheSame(oldItem: AlumnusProfileItem, newItem: AlumnusProfileItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: AlumnusProfileItem, newItem: AlumnusProfileItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}



