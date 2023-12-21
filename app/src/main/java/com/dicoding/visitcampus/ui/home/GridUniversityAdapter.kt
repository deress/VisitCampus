package com.dicoding.visitcampus.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.database.UnivEntity
import com.dicoding.visitcampus.databinding.GridUniversityBinding
import com.dicoding.visitcampus.ui.university.detail.DetailUniversityActivity

class GridUniversityAdapter: ListAdapter<UnivEntity, GridUniversityAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(val binding: GridUniversityBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(university: UnivEntity){
            val context = itemView.context
            val logoResId = context.resources.getIdentifier(university.univLogo, "drawable", context.packageName)

            binding.imgItemPhoto.setImageResource(logoResId)
            binding.tvItemName.text = university.univName

            itemView.setOnClickListener {
                val intentDetail = Intent(itemView.context, DetailUniversityActivity::class.java)
                intentDetail.putExtra(DetailUniversityActivity.EXTRA_UNIV_ID, university.universityId)
                itemView.context.startActivity(intentDetail)
            }
        }
    }

    fun submitListRandomly(list: List<UnivEntity>?) {
        list?.let {
            val shuffledList = list.toMutableList().apply { shuffle() }
            submitList(shuffledList)
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

//    override fun getItemCount(): Int {
//        return 2
//    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UnivEntity>() {
            override fun areItemsTheSame(oldItem: UnivEntity, newItem: UnivEntity): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: UnivEntity, newItem: UnivEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}


