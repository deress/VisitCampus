package com.dicoding.visitcampus.ui.university.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.data.model.AlumnusProfile
import com.dicoding.visitcampus.data.model.CollegeAchievement
import com.dicoding.visitcampus.data.model.Faculty
import com.dicoding.visitcampus.data.model.RegistrationPath
import com.dicoding.visitcampus.data.model.University
import com.dicoding.visitcampus.data.model.UniversityData
import com.dicoding.visitcampus.databinding.ActivityDetailUniversityBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.university.detail.adapter.AlumnusProfileAdapter
import com.dicoding.visitcampus.ui.university.detail.adapter.FacultyMajorAdapter
import com.dicoding.visitcampus.ui.university.detail.adapter.ListAchievementAdapter
import com.dicoding.visitcampus.ui.university.detail.adapter.RegistrationPathAdapter
import com.google.android.material.appbar.MaterialToolbar

class DetailUniversityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUniversityBinding
    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUniversityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val univName = intent.getStringExtra(EXTRA_UNIV_NAME).toString()
        val univResult = viewModel.getUnivById(univName)

        val toolbar : MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }



        val layoutManager1 = LinearLayoutManager(this)
        binding.rvCollegeAchievement.layoutManager = layoutManager1
        val layoutManager2 = LinearLayoutManager(this)
        binding.rvAlumnusProfile.layoutManager = layoutManager2
        val layoutManager3 = LinearLayoutManager(this)
        binding.rvRegistrationPath.layoutManager = layoutManager3
        val layoutManager4 = LinearLayoutManager(this)
        binding.rvFacultyMajor.layoutManager = layoutManager4


        setDetailData(univResult)
        setAchievementData(univResult.collegeAchievement)
        setAlumnusData(univResult.alumnusProfile)
        setPathData(univResult.registrationPath)
        setFacultyMajorData(UniversityData.dummyFacultyMajor)


    }


    private fun setDetailData(item: University) {
        binding.apply {
            ivCoverUniv.setImageResource(item.coverPhoto)
            ivLogoUniv.setImageResource(item.logoPhoto)
            tvNameUniv.text = item.univName
            tvContentDescription.text = item.univDescription
        }

    }

    private fun setAchievementData(items: List<CollegeAchievement>) {
        val adapter = ListAchievementAdapter()
        adapter.submitList(items)
        binding.rvCollegeAchievement.adapter = adapter
    }

    private fun setAlumnusData(items: List<AlumnusProfile>) {
        val adapter = AlumnusProfileAdapter()
        adapter.submitList(items)
        binding.rvAlumnusProfile.adapter = adapter
    }

    private fun setPathData(items: List<RegistrationPath>) {
        val adapter = RegistrationPathAdapter()
        adapter.submitList(items)
        binding.rvRegistrationPath.adapter = adapter
    }

    private fun setFacultyMajorData(items: List<Any>) {
        val adapter = FacultyMajorAdapter(items)
        binding.rvFacultyMajor.adapter = adapter
    }

    companion object {
        const val EXTRA_UNIV_NAME = "univ_NAME"
    }
}