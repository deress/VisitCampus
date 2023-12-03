package com.dicoding.visitcampus.ui.major

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.ActivityMajorRecomendationBinding
import com.google.android.material.appbar.MaterialToolbar
import java.util.Timer

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
        val progressDialog = ProgressDialog(this@MajorRecomendationActivity)

        binding.btnCheckRecomendation.setOnClickListener {
            progressDialog.setTitle("Checking")
            progressDialog.setMessage("The system is working, please wait...")
            progressDialog.show()
            Handler().postDelayed({
                progressDialog.hide()
                val intent = Intent(this, ResultMajorRecomendationActivity::class.java)
                startActivity(intent)
                this@MajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }, 5000)
        }

        playAnimation()
    }

    private fun playAnimation() {
        val tvMajorRecomendation = ObjectAnimator.ofFloat(binding.tvMajorRecomendation, View.ALPHA, 1f).setDuration(500)
        val tvText1 = ObjectAnimator.ofFloat(binding.tvText1, View.ALPHA, 1f).setDuration(500)
        val tvText2 = ObjectAnimator.ofFloat(binding.tvText2, View.ALPHA, 1f).setDuration(500)
        val etMessageBox = ObjectAnimator.ofFloat(binding.etMessageBox, View.ALPHA, 1f).setDuration(500)
        val btnCheckRecomendation = ObjectAnimator.ofFloat(binding.btnCheckRecomendation, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(tvMajorRecomendation, tvText1, tvText2, etMessageBox, btnCheckRecomendation)
            start()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@MajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}