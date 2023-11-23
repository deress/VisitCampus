package com.dicoding.visitcampus.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.University
import com.dicoding.visitcampus.databinding.GridUniversityBinding
import com.dicoding.visitcampus.databinding.ItemUniversityBinding
import com.dicoding.visitcampus.ui.university.detail.DetailUniversityActivity

class GridUniversityAdapter: ListAdapter<University, GridUniversityAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(val binding: GridUniversityBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(university: University){
            binding.imgItemPhoto.setImageResource(university.logoPhoto)
            binding.tvItemName.text = university.univName

            itemView.setOnClickListener {
                val intentDetail = Intent(itemView.context, DetailUniversityActivity::class.java)
                intentDetail.putExtra(DetailUniversityActivity.EXTRA_UNIV_NAME, university.univName)
                itemView.context.startActivity(intentDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = GridUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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


