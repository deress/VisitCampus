package com.dicoding.visitcampus.ui.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.exam.Exam
import com.dicoding.visitcampus.data.model.exam.Explanation
import com.dicoding.visitcampus.databinding.ExamItemLayoutBinding
import com.dicoding.visitcampus.databinding.ExplanationItemLayoutBinding

class ExplanationListAdapter: ListAdapter<Explanation, ExplanationListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback
    class ListViewHolder(val binding: ExplanationItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(explanation: Explanation){
            binding.tvQuestion.text = explanation.question
            binding.tvAnswer.text = explanation.answer
            binding.tvExplanationDescription.text = explanation.explanationDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ExplanationItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Explanation>() {
            override fun areItemsTheSame(oldItem: Explanation, newItem: Explanation): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Explanation, newItem: Explanation): Boolean {
                return oldItem == newItem
            }
        }
    }
}