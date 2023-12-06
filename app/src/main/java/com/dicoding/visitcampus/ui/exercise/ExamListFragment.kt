package com.dicoding.visitcampus.ui.exercise

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.data.model.exam.Exam
import com.dicoding.visitcampus.data.model.exam.ExamData
import com.dicoding.visitcampus.databinding.FragmentExamListBinding

class ExamListFragment : Fragment() {
    private var _binding: FragmentExamListBinding? = null
    private val binding get() = _binding!!

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

        showListExam(ExamData.examList)
    }

    private fun showListExam(items: List<Exam>) {
        Log.i("ExamListFragment", "examList: $items")
        val adapter = ExamListAdapter()
        adapter.submitList(items)
        binding.rvExam.adapter = adapter

        adapter.setOnItemClickCallback(object : ExamListAdapter.OnItemClickCallback {
            override fun onItemClicked() {
                val intent = Intent(requireActivity(), ExamQuestionActivity::class.java)
                startActivity(intent)
            }
        })
    }
}