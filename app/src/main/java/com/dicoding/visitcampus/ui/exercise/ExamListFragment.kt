package com.dicoding.visitcampus.ui.exercise

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.response.ExamsResponse
import com.dicoding.visitcampus.databinding.FragmentExamListBinding
import com.dicoding.visitcampus.ui.ViewModelFactory

class ExamListFragment : Fragment() {
    private var _binding: FragmentExamListBinding? = null
    private val binding get() = _binding!!
    private val examViewModel: ExamViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExamListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvExam.layoutManager = layoutManager

        examViewModel.exams()
        examViewModel.examListResult.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Result.Loading -> showLoading(true)
                    is Result.Success ->
                    {
                        showLoading(false)
                        val response = it.data
                        Log.i("ExamListFragment", "response: $response")
                        showListExam(response)
                    }
                    is Result.Error -> {
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun showListExam(items: List<ExamsResponse>) {
        Log.i("ExamListFragment", "examList: $items")
        val adapter = ExamListAdapter(items)
        adapter.submitList(items)
        binding.rvExam.adapter = adapter

        adapter.setOnItemClickCallback(object : ExamListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ExamsResponse) {
                val intent = Intent(requireActivity(), ExamQuestionActivity::class.java)
                intent.putExtra(ExamQuestionActivity.PRACTICE_ID, data.practiceId)
                startActivity(intent)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}