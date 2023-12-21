package com.dicoding.visitcampus.ui.major

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.model.major.MajorRecomendation
import com.dicoding.visitcampus.databinding.ActivityResultMajorRecomendationBinding
import com.dicoding.visitcampus.ui.main.MainActivity
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.main.MainViewModel
import com.google.android.material.appbar.MaterialToolbar

class ResultMajorRecomendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultMajorRecomendationBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private val majorRecomendationViewModel by viewModels<MajorRecomendationViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityResultMajorRecomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        viewModel.getSession().observe(this) {user ->
            majorRecomendationViewModel.getMajorRecomendation(user.userId).observe(this) {
                setData(it)
            }
        }

        binding.btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            this@ResultMajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.btnTryAgain.setOnClickListener {
            val intent = Intent(this, MajorRecomendationActivity::class.java)
            startActivity(intent)
            this@ResultMajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        playAnimation()
    }

    @SuppressLint("StringFormatInvalid")
    private fun setData(data: MajorRecomendation) {
        val saintekPercent = data.saintek * 100
        val soshumPercent = data.soshum * 100
        val saintekText = getString(R.string.saintek_percent, saintekPercent.toInt())
        val soshumText = getString(R.string.saintek_percent, soshumPercent.toInt())
        binding.saintekPercent.text = saintekText + " %"
        binding.soshumPercent.text = soshumText + " %"
        binding.percentageBar.progress = saintekPercent.toInt()
        if (saintekPercent > soshumPercent) {
            binding.tvResult.text = getString(R.string.saintek_result)
        } else {
            binding.tvResult.text = getString(R.string.soshum_result)
        }
    }

    private fun playAnimation() {
        val tvTitle = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(1000)
        val tvResult = ObjectAnimator.ofFloat(binding.tvResult, View.ALPHA, 1f).setDuration(1000)
        val percentageBar = ObjectAnimator.ofFloat(binding.percentageBar, View.ALPHA, 1f).setDuration(1000)
        val tvSaintek = ObjectAnimator.ofFloat(binding.tvSaintek, View.ALPHA, 1f).setDuration(1000)
        val tvSoshum = ObjectAnimator.ofFloat(binding.tvSoshum, View.ALPHA, 1f).setDuration(1000)
        val saintekPercent = ObjectAnimator.ofFloat(binding.saintekPercent, View.ALPHA, 1f).setDuration(1000)
        val soshumPercent = ObjectAnimator.ofFloat(binding.soshumPercent, View.ALPHA, 1f).setDuration(1000)
        val btnFinish = ObjectAnimator.ofFloat(binding.btnFinish, View.ALPHA, 1f).setDuration(1000)
        val btnTryAgain = ObjectAnimator.ofFloat(binding.btnTryAgain, View.ALPHA, 1f).setDuration(1000)


        AnimatorSet().apply {
            playTogether(tvTitle,tvResult, btnFinish, btnTryAgain, percentageBar, tvSaintek, tvSoshum, saintekPercent, soshumPercent)
            start()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        this@ResultMajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    companion object{
        const val RESULT_RECOMENDATION = "result_recomendation"
    }
}