package com.dicoding.visitcampus.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.model.University
import com.dicoding.visitcampus.data.model.UniversityData
import com.dicoding.visitcampus.databinding.FragmentHomeBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.university.ListUniversityAdapter
import com.dicoding.visitcampus.ui.university.UniversityFragment
import com.dicoding.visitcampus.ui.university.UniversityViewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvUniversities.layoutManager = layoutManager
        showListUniversity(viewModel.university)

        val universityFragment = UniversityFragment()
        binding.tvViewAll.setOnClickListener{
            setCurrentFragment(universityFragment)
        }
    }

    private fun showListUniversity(items: List<University>) {
        val adapter = GridUniversityAdapter()
        adapter.submitList(items)
        binding.rvUniversities.adapter = adapter
    }

    private fun setCurrentFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.frame_container,fragment)
            commit()
        }
    }


}