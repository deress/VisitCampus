package com.dicoding.visitcampus.ui.university

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.database.UnivEntity
import com.dicoding.visitcampus.databinding.FragmentUniversitiesBinding
import com.dicoding.visitcampus.ui.ViewModelFactory

class UniversityFragment : Fragment(){
    private var _binding: FragmentUniversitiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UniversityViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUniversitiesBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFilter.setOnClickListener {
            val filter = FilterFragment()
            val fragmentManager = childFragmentManager
            filter.show(fragmentManager, FilterFragment.TAG)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    if (searchView.text.toString() == "") {
                        viewModel.search("")
                        false
                    } else {
                        val keyword = searchView.text.toString()
                        viewModel.search(keyword)
                        false
                    }
                }
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvUniversities.layoutManager = layoutManager

        viewModel.getUniversities().observe(viewLifecycleOwner) {result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showListUniversity(result.data)
                    showLoading(false)
                }
                is Result.Error -> {
                    showLoading(false)
                }
                else -> {}
            }
        }
    }

    private fun showListUniversity(items: List<UnivEntity>) {
        val adapter = ListUniversityAdapter()
        adapter.submitListAlphabetically(items)
        binding.rvUniversities.adapter = adapter
    }

    private fun showLoading(isLoading:Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}