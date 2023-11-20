package com.dicoding.visitcampus.ui.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.data.model.University
import com.dicoding.visitcampus.data.model.UniversityData
import com.dicoding.visitcampus.databinding.FragmentUniversitiesBinding
import com.dicoding.visitcampus.ui.ViewModelFactory


class UniversityFragment : Fragment() {
    private var _binding: FragmentUniversitiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UniversityViewModel> {
        ViewModelFactory.getInstance()
    }

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
        showListUniversity(viewModel.university)


//        viewModel.getUniversity().observe(viewLifecycleOwner) {result ->
//            when (result) {
//                is Result.Loading -> {
//                    showLoading(true)
//                }
//                is Result.Success -> {
//                    showListUniversity(result.data)
//                    showLoading(false)
//                }
//                is Result.Error -> {
//                    Log.d("UniversityViewModel", result.error)
//                    showLoading(false)
//                }
//            }
//        }



    }

    private fun showListUniversity(items: List<University>) {
        val adapter = ListUniversityAdapter()
        adapter.submitList(items)
        binding.rvUniversities.adapter = adapter
    }

    private fun showLoading(isLoading:Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }
}