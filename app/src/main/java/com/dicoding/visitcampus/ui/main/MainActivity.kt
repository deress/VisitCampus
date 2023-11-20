package com.dicoding.visitcampus.ui.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.ActivityMainBinding
import com.dicoding.visitcampus.ui.home.HomeFragment
import com.dicoding.visitcampus.ui.university.UniversityFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView


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
        val universityFragment = UniversityFragment()
        setCurrentFragment(homeFragment)

        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.list_university -> setCurrentFragment(universityFragment)
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


