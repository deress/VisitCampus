package com.dicoding.visitcampus.ui.university.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.response.FacultyItem
import com.dicoding.visitcampus.databinding.ItemFacultyBinding

class FacultyMajorAdapter: ListAdapter<FacultyItem, FacultyMajorAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private var isExpandable = false
    class ListViewHolder(val binding: ItemFacultyBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(faculty: FacultyItem){
            val majorAdapter = MajorAdapter()
            binding.apply {
                tvFacultyName.text = faculty.facultyName

                rvMajor.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL,false)
                rvMajor.adapter = majorAdapter
                majorAdapter.submitList(faculty.major)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemFacultyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val faculty = getItem(position)
        holder.bind(faculty)

        holder.binding.apply {
            if (isExpandable) {
                rvMajor.visibility = View.VISIBLE
                ivArrow.setImageResource(R.drawable.baseline_arrow_drop_up_24)
            } else {
                rvMajor.visibility = View.GONE
                ivArrow.setImageResource(R.drawable.baseline_arrow_drop_down_24)
            }

            constraintLayout.setOnClickListener {
                isExpandable = !isExpandable
                notifyItemChanged(position)
            }
        }

    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FacultyItem>() {
            override fun areItemsTheSame(oldItem: FacultyItem, newItem: FacultyItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: FacultyItem, newItem: FacultyItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

