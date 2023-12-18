package com.dicoding.visitcampus.ui.university

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.databinding.FragmentUniversitiesBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.response.UnivItem

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
        // Inflate the layout for this fragment
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
                    Toast.makeText(activity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
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
                    showListUniversity(result.data.univ)
                    showLoading(false)
                }
                is Result.Error -> {
                    Log.d("UniversityViewModel", result.error)
                    showLoading(false)
                }

                else -> {}
            }
        }
    }

    private fun showListUniversity(items: List<UnivItem>) {
        val adapter = ListUniversityAdapter()
        adapter.submitList(items)
        binding.rvUniversities.adapter = adapter
    }

    private fun showLoading(isLoading:Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }


}