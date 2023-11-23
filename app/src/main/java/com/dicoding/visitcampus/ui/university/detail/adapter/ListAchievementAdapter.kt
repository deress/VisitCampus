package com.dicoding.visitcampus.ui.university.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.CollegeAchievement
import com.dicoding.visitcampus.databinding.ItemCollegeAchievementBinding

class ListAchievementAdapter: ListAdapter<CollegeAchievement, ListAchievementAdapter.ListViewHolder>(
    DIFF_CALLBACK
) {
    class ListViewHolder(val binding: ItemCollegeAchievementBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(achievement: CollegeAchievement){
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CollegeAchievement>() {
            override fun areItemsTheSame(oldItem: CollegeAchievement, newItem: CollegeAchievement): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: CollegeAchievement, newItem: CollegeAchievement): Boolean {
                return oldItem == newItem
            }
        }
    }
}