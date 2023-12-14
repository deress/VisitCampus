package com.dicoding.visitcampus.ui.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.exam.Exam
import com.dicoding.visitcampus.databinding.ExamItemLayoutBinding

class ExamListAdapter: ListAdapter<Exam, ExamListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(val binding: ExamItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(exam: Exam){
            binding.tvExamName.text = exam.examName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ExamItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val exam = getItem(position)
        holder.bind(exam)

        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked()
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked()
    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Exam>() {
            override fun areItemsTheSame(oldItem: Exam, newItem: Exam): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Exam, newItem: Exam): Boolean {
                return oldItem == newItem
            }
        }
    }
}