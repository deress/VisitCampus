package com.dicoding.visitcampus.ui.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.response.ResultExamResponse
import com.dicoding.visitcampus.databinding.ExplanationItemLayoutBinding

class ExplanationListAdapter: ListAdapter<ResultExamResponse, ExplanationListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(val binding: ExplanationItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(explanation: ResultExamResponse){
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
    }

    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultExamResponse>() {
            override fun areItemsTheSame(oldItem: ResultExamResponse, newItem: ResultExamResponse): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ResultExamResponse, newItem: ResultExamResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}