package com.dicoding.visitcampus.ui.university


import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.database.UnivEntity
import com.dicoding.visitcampus.databinding.ItemUniversityBinding
import com.dicoding.visitcampus.ui.university.detail.DetailUniversityActivity

class ListUniversityAdapter: ListAdapter<UnivEntity, ListUniversityAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(val binding: ItemUniversityBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(university: UnivEntity){
            val context = itemView.context
            val imgResId = context.resources.getIdentifier(university.univLogo, "drawable", context.packageName)

            binding.imgItemPhoto.setImageResource(imgResId)
            binding.tvItemName.text = university.univName

            itemView.setOnClickListener {
                val intentDetail = Intent(itemView.context, DetailUniversityActivity::class.java)
                intentDetail.putExtra(DetailUniversityActivity.EXTRA_UNIV_ID, university.universityId)
                itemView.context.startActivity(intentDetail)
            }
        }
    }

    fun submitListAlphabetically(list: List<UnivEntity>?) {
        list?.let {
            val sortedList = list.sortedBy { it.univName }
            submitList(sortedList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val university = getItem(position)
        holder.bind(university)

    }

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


