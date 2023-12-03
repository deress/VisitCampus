package com.dicoding.visitcampus.ui.major

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.ActivityResultMajorRecomendationBinding
import com.dicoding.visitcampus.databinding.FragmentHomeBinding
import com.dicoding.visitcampus.ui.home.HomeFragment
import com.dicoding.visitcampus.ui.main.MainActivity
import com.dicoding.visitcampus.ui.university.UniversitiesFragment

class ResultMajorRecomendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultMajorRecomendationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultMajorRecomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this@ResultMajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        playAnimation()
    }

    private fun playAnimation() {
        val tvTitle = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(500)
        val percentageBar = ObjectAnimator.ofFloat(binding.percentageBar, View.ALPHA, 1f).setDuration(500)
        val tvProgress = ObjectAnimator.ofFloat(binding.tvProgress, View.ALPHA, 1f).setDuration(500)
        val tvResult = ObjectAnimator.ofFloat(binding.tvResult, View.ALPHA, 1f).setDuration(500)
        val btnFinish = ObjectAnimator.ofFloat(binding.btnFinish, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(tvTitle,percentageBar, tvProgress, tvResult, btnFinish)
            start()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@ResultMajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}