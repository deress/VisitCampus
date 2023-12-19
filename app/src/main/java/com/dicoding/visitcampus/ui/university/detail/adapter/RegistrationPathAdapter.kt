package com.dicoding.visitcampus.ui.university.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.response.RegistrationPathItem
import com.dicoding.visitcampus.databinding.ItemRegistrationPathBinding

class RegistrationPathAdapter: ListAdapter<RegistrationPathItem, RegistrationPathAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private var isExpandable = false

    class ListViewHolder(val binding: ItemRegistrationPathBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(path: RegistrationPathItem){
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

        holder.binding.apply {
            if (isExpandable) {
                tvPathDescription.visibility = View.VISIBLE
                ivArrow.setImageResource(R.drawable.baseline_arrow_drop_up_24)
            } else {
                tvPathDescription.visibility = View.GONE
                ivArrow.setImageResource(R.drawable.baseline_arrow_drop_down_24)
            }

            constraintLayout.setOnClickListener {
                isExpandable = !isExpandable
                notifyItemChanged(position)
            }
        }
    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RegistrationPathItem>() {
            override fun areItemsTheSame(oldItem: RegistrationPathItem, newItem: RegistrationPathItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: RegistrationPathItem, newItem: RegistrationPathItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}


