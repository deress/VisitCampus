package com.dicoding.visitcampus.ui.major

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.ActivityMajorRecomendationBinding
import com.google.android.material.appbar.MaterialToolbar

class MajorRecomendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMajorRecomendationBinding
    private val questions: Array<String> = arrayOf("1. Soal pertama", "2. Soal kedua", "3. Soal ketiga", "4. Soal keempat")
    private var currentQuestion = 0
    private val result: ArrayList<String> = arrayListOf()
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

        binding.tvQuestion.text = questions[currentQuestion]

        binding.btnCheckRecomendation.setOnClickListener {
            currentQuestion++
            Log.i("MajorRecomendationActivity", "$currentQuestion")
            if (questions.size == currentQuestion) {
                result.add(binding.etMessageBox.text.toString())
                progressDialog.setTitle("Checking")
                progressDialog.setMessage("The system is working, please wait...")
                progressDialog.show()
                Handler().postDelayed({
                    progressDialog.hide()
                    val intent = Intent(this, ResultMajorRecomendationActivity::class.java)
                    startActivity(intent)
                    this@MajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }, 5000)
            } else {
                if (questions.size - 1 == currentQuestion) {
                    binding.btnCheckRecomendation.setText("CHECK")
                } else {
                    binding.btnCheckRecomendation.setText("NEXT")
                }
                binding.tvQuestion.text = questions[currentQuestion]
                result.add(binding.etMessageBox.text.toString())
            }
            Log.i("MajorRecomendationActivity", "result: $result")
        }

        playAnimation()
    }

    private fun playAnimation() {
        val tvMajorRecomendation = ObjectAnimator.ofFloat(binding.tvMajorRecomendation, View.ALPHA, 1f).setDuration(500)
        val tvText1 = ObjectAnimator.ofFloat(binding.tvQuestion, View.ALPHA, 1f).setDuration(500)
        val tvText2 = ObjectAnimator.ofFloat(binding.tvQuestion2, View.ALPHA, 1f).setDuration(500)
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