package com.dicoding.visitcampus.ui.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.model.University
import com.dicoding.visitcampus.data.model.UniversityData
import com.dicoding.visitcampus.databinding.FragmentHomeBinding
import com.dicoding.visitcampus.databinding.FragmentUniversitiesBinding


class UniversitiesFragment : Fragment() {
    private var _binding: FragmentUniversitiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUniversitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvUniversities.layoutManager = layoutManager

        showListUniversity(UniversityData.dummyUniversity)

    }

    private fun showListUniversity(items: List<University>) {
        val adapter = ListUniversityAdapter()
        adapter.submitList(items)
        binding.rvUniversities.adapter = adapter
    }
}