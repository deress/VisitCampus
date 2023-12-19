package com.dicoding.visitcampus.ui.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.exam.Exam
import com.dicoding.visitcampus.data.response.ExamsResponse
import com.dicoding.visitcampus.databinding.ExamItemLayoutBinding

class ExamListAdapter(private val examsResponse: List<ExamsResponse>): ListAdapter<ExamsResponse, ExamListAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(val binding: ExamItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(exam: ExamsResponse){
            binding.tvExamName.text = exam.title
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
            onItemClickCallback.onItemClicked(examsResponse[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ExamsResponse)
    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExamsResponse>() {
            override fun areItemsTheSame(oldItem: ExamsResponse, newItem: ExamsResponse): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ExamsResponse, newItem: ExamsResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}