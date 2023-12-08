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
import androidx.core.widget.doAfterTextChanged
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.ActivityMajorRecomendationBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Answers(
    @SerializedName("answers")
    val answers: Map<String, List<String>>
)

class MajorRecomendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMajorRecomendationBinding
    private val categories: Array<String> = arrayOf("1. Category one", "2. Category two", "3. Category three", "4. Category four")
    private val questions: Array<String> = arrayOf("This is question one?", "This is question two?", "This is question three?", "This is question four?")
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
        setRegisterEnable()

        binding.tvQuestion.text = categories[currentQuestion]
        binding.tvQuestion2.text = questions[currentQuestion]
        binding.etMessageBox.doAfterTextChanged { setRegisterEnable() }

        binding.btnCheckRecomendation.setOnClickListener {
            currentQuestion++
            binding.progressBar.progress = currentQuestion
            binding.tvProgress.text = "$currentQuestion" + "/" + "${questions!!.size}"
            Log.i("MajorRecomendationActivity", "$currentQuestion")
            if (questions.size == currentQuestion) {
                result.add(binding.etMessageBox.text.toString())
                val answers = Answers(
                    mapOf(
                        "EI" to listOf(result[0]),
                        "SN" to listOf(result[1]),
                        "TF" to listOf(result[2]),
                        "JP" to listOf(result[3])
                    )
                )
                val gson = Gson()
                val jsonResult = gson.toJson(answers)
                Log.i("MajorRecomendationActivity", "result: $result")
                Log.i("MajorRecomendationActivity", "jsonResult: $jsonResult")

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
                binding.tvQuestion.text = categories[currentQuestion]
                binding.tvQuestion2.text = questions[currentQuestion]
                result.add(binding.etMessageBox.text.toString())
                binding.etMessageBox.text.clear()
            }

            Log.i("MajorRecomendationActivity", "result: $result")
        }

        playAnimation()
    }

    private fun setRegisterEnable() {
        binding.btnCheckRecomendation.isEnabled =
            (!binding.etMessageBox.text.isNullOrEmpty())
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