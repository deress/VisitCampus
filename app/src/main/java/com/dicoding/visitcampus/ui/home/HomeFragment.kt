package com.dicoding.visitcampus.ui.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.response.UnivItem
import com.dicoding.visitcampus.databinding.FragmentHomeBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.university.UniversityFragment


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
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

        with(binding) {
            val carouselAdapter = CarouselHomeAdapter()
            val viewPager = carouselPager

            viewPager.apply {
                carouselAdapter.submitList(viewModel.carouselPhoto)
                adapter = carouselAdapter

                dotsIndicator.attachTo(this)
            }
        }


        val layoutManager = GridLayoutManager(requireActivity(), 2, RecyclerView.VERTICAL, false)
        binding.rvUniversities.layoutManager = layoutManager
//        showListUniversity(viewModel.university)

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
                    Log.d("HomeViewModel", result.error)
                    showLoading(false)
                }

                else -> {}
            }
        }

//        val universityFragment = UniversityFragment()
//        binding.tvViewAll.setOnClickListener{
//            setCurrentFragment(universityFragment)
//        }
    }

    private fun showListUniversity(items: List<UnivItem>) {
        val adapter = GridUniversityAdapter()
        adapter.submitList(items)
        binding.rvUniversities.adapter = adapter

        val majorButton = ObjectAnimator.ofFloat(binding.btnMajorrecom, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            startDelay = 100
            playSequentially(majorButton)
            start()
        }
    }

//    private fun setCurrentFragment(fragment: Fragment) {
//        val fragmentManager = parentFragmentManager
//        fragmentManager.beginTransaction().apply {
//            replace(R.id.frame_container,fragment)
//            addToBackStack(null)
//            commit()
//        }
//    }

    private fun showLoading(isLoading:Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }


}