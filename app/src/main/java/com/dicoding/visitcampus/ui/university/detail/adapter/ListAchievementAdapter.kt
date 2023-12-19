package com.dicoding.visitcampus.ui.university.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.response.CollegeAchievementItem
import com.dicoding.visitcampus.databinding.ItemCollegeAchievementBinding

class ListAchievementAdapter: ListAdapter<CollegeAchievementItem, ListAchievementAdapter.ListViewHolder>(
    DIFF_CALLBACK
) {
    class ListViewHolder(val binding: ItemCollegeAchievementBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(achievement: CollegeAchievementItem){
            binding.tvAchievementName.text = achievement.achievementName
            binding.tvAchievementDate.text = achievement.achievementDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCollegeAchievementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val achievement = getItem(position)
        holder.bind(achievement)
    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CollegeAchievementItem>() {
            override fun areItemsTheSame(oldItem: CollegeAchievementItem, newItem: CollegeAchievementItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: CollegeAchievementItem, newItem: CollegeAchievementItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}