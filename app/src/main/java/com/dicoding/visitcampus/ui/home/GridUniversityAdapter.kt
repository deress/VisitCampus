package com.dicoding.visitcampus.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.response.UnivItem
import com.dicoding.visitcampus.databinding.GridUniversityBinding
import com.dicoding.visitcampus.ui.university.detail.DetailUniversityActivity

class GridUniversityAdapter: ListAdapter<UnivItem, GridUniversityAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(val binding: GridUniversityBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(university: UnivItem){
            val context = itemView.context
            val logoResId = context.resources.getIdentifier(university.logoPhoto, "drawable", context.packageName)

            binding.imgItemPhoto.setImageResource(logoResId)
            binding.tvItemName.text = university.univName

            itemView.setOnClickListener {
                val intentDetail = Intent(itemView.context, DetailUniversityActivity::class.java)
                intentDetail.putExtra(DetailUniversityActivity.EXTRA_UNIV_ID, university.id)
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UnivItem>() {
            override fun areItemsTheSame(oldItem: UnivItem, newItem: UnivItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: UnivItem, newItem: UnivItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}


