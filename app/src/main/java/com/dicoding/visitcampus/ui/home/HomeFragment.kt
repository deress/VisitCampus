package com.dicoding.visitcampus.ui.home


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.database.UnivEntity
import com.dicoding.visitcampus.databinding.FragmentHomeBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.main.MainViewModel
import com.dicoding.visitcampus.ui.major.MajorRecomendationActivity
import com.dicoding.visitcampus.ui.major.MajorRecomendationViewModel
import com.dicoding.visitcampus.ui.major.ResultMajorRecomendationActivity


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val majorRecomendationViewModel by viewModels<MajorRecomendationViewModel> {
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

        binding.btnRecomendation.setOnClickListener{
            activity?.let{bind ->
                mainViewModel.getSession().observe(viewLifecycleOwner){ user ->
                    majorRecomendationViewModel.getMajorRecomendation(user.userId).observe(viewLifecycleOwner) {
                        if (it != null) {
                            val intent = Intent (requireContext(), ResultMajorRecomendationActivity::class.java)
                            bind.startActivity(intent)
                            bind.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        } else {
                            val intent = Intent (requireContext(), MajorRecomendationActivity::class.java)
                            bind.startActivity(intent)
                            bind.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        }
                    }
                }
            }
        }

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


        viewModel.getUniversities().observe(viewLifecycleOwner){ result ->
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
        val adapter = GridUniversityAdapter()
        adapter.submitListRandomly(items)
        binding.rvUniversities.adapter = adapter

        val majorButton = ObjectAnimator.ofFloat(binding.btnRecomendation, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            startDelay = 100
            playSequentially(majorButton)
            start()
        }
    }

    private fun showLoading(isLoading:Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }



}