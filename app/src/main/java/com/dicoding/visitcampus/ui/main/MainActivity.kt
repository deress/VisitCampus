package com.dicoding.visitcampus.ui.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.ActivityMainBinding
import com.dicoding.visitcampus.ui.exercise.ExamListFragment
import com.dicoding.visitcampus.ui.home.HomeFragment
import com.dicoding.visitcampus.ui.university.UniversitiesFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var binding: ActivityMainBinding
private lateinit var bottomNavigationView: BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }

        val homeFragment = HomeFragment()
        val universitiesFragment = UniversitiesFragment()
        val examListFragment = ExamListFragment()
        setCurrentFragment(homeFragment)

        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.list_university -> setCurrentFragment(universitiesFragment)
                R.id.exam_practice -> setCurrentFragment(examListFragment)
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_container,fragment)
            commit()
        }
    }
}


