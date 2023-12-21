package com.dicoding.visitcampus.ui.university.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.response.AlumnusProfileItem
import com.dicoding.visitcampus.data.response.CollegeAchievementItem
import com.dicoding.visitcampus.data.response.FacultyItem
import com.dicoding.visitcampus.data.response.RegistrationPathItem
import com.dicoding.visitcampus.data.response.UniversitiesResponseItem
import com.dicoding.visitcampus.databinding.ActivityDetailUniversityBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.university.detail.adapter.AlumnusProfileAdapter
import com.dicoding.visitcampus.ui.university.detail.adapter.FacultyMajorAdapter
import com.dicoding.visitcampus.ui.university.detail.adapter.ListAchievementAdapter
import com.dicoding.visitcampus.ui.university.detail.adapter.RegistrationPathAdapter
import com.dicoding.visitcampus.ui.university.streetView.StreetViewActivity
import com.dicoding.visitcampus.ui.university.streetView.StreetViewActivity.Companion.LATITUDE
import com.dicoding.visitcampus.ui.university.streetView.StreetViewActivity.Companion.LONGITUDE
import com.google.android.material.appbar.MaterialToolbar

class DetailUniversityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUniversityBinding
    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUniversityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val univId = intent.getIntExtra(EXTRA_UNIV_ID, 0)

        val toolbar : MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val facultyMajorLayout = LinearLayoutManager(this)
        binding.rvFacultyMajor.layoutManager = facultyMajorLayout

        val achievementLayout = LinearLayoutManager(this)
        binding.rvCollegeAchievement.layoutManager = achievementLayout
        val achievementDecoration = DividerItemDecoration(this, achievementLayout.orientation)
        binding.rvCollegeAchievement.addItemDecoration(achievementDecoration)

        val alumnusLayout = LinearLayoutManager(this)
        binding.rvAlumnusProfile.layoutManager = alumnusLayout
        val alumnusDecoration = DividerItemDecoration(this, alumnusLayout.orientation)
        binding.rvAlumnusProfile.addItemDecoration(alumnusDecoration)

        val layoutManager4 = LinearLayoutManager(this)
        binding.rvRegistrationPath.layoutManager = layoutManager4

        viewModel.getUniv(univId).observe(this) {result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    setDetailData(result.data)
                    setAchievementData(result.data.achievementUniversity)
                    setAlumnusData(result.data.profileAlumnus)
                    setPathData(result.data.registrationPath)
                    setFacultyMajorData(result.data.faculties)
                    showLoading(false)
                }
                is Result.Error -> {
                    Log.d("DetailUniversityViewModel", result.error)
                    showLoading(false)
                }

                else -> {}
            }
        }
    }

    private fun setDetailData(item: UniversitiesResponseItem) {
        binding.apply {
            val logoResId = resources.getIdentifier(item.univLogo, "drawable", packageName)
            val coverResId = resources.getIdentifier(item.univCover, "drawable", packageName)

            ivCoverUniv.setImageResource(coverResId)
            ivLogoUniv.setImageResource(logoResId)
            tvNameUniv.text = item.univName
            tvContentDescription.text = item.personalityUniv

            btnStreetView.setOnClickListener{
               moveStreetView(item.latitude, item.longitude)
            }
        }

    }

    private fun setFacultyMajorData(items: List<FacultyItem>) {
        val adapter = FacultyMajorAdapter()
        adapter.submitList(items)
        binding.rvFacultyMajor.adapter = adapter
    }

    private fun setAchievementData(items: List<CollegeAchievementItem>) {
        val adapter = ListAchievementAdapter()
        adapter.submitList(items)
        binding.rvCollegeAchievement.adapter = adapter
    }

    private fun setAlumnusData(items: List<AlumnusProfileItem>) {
        val adapter = AlumnusProfileAdapter()
        adapter.submitList(items)
        binding.rvAlumnusProfile.adapter = adapter

    }

    private fun setPathData(items: List<RegistrationPathItem>) {
        val adapter = RegistrationPathAdapter()
        adapter.submitList(items)
        binding.rvRegistrationPath.adapter = adapter
    }

    private fun moveStreetView(latitude: Double, longitude: Double){
        val intent = Intent(this, StreetViewActivity::class.java)
        intent.putExtra(LATITUDE, latitude)
        intent.putExtra(LONGITUDE, longitude)
        startActivity(intent)
    }

    private fun showLoading(isLoading:Boolean) {
        binding.apply {
            if (isLoading){
               progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                btnStreetView.visibility = View.VISIBLE
                containerDescription.visibility = View.VISIBLE
                containerFacultyAndMajor.visibility = View.VISIBLE
                containerCollegeAchievement.visibility = View.VISIBLE
                containerAlumnusProfile.visibility = View.VISIBLE
                containerRegistrationPath.visibility = View.VISIBLE

            }
        }

    }



    companion object {
        const val EXTRA_UNIV_ID = "univ_ID"
    }
}