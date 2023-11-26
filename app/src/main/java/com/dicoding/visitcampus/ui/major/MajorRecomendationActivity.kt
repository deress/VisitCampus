package com.dicoding.visitcampus.ui.major

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.visitcampus.databinding.ActivityMajorRecomendationBinding
import com.google.android.material.appbar.MaterialToolbar

class MajorRecomendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMajorRecomendationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMajorRecomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }

        binding.btnCheckRecomendation.setOnClickListener {
            val intent = Intent(this, ResultMajorRecomendationActivity::class.java)
            startActivity(intent)
        }
    }
}