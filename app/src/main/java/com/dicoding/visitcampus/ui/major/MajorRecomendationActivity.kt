package com.dicoding.visitcampus.ui.major

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.request.RequestPredictBody
import com.dicoding.visitcampus.databinding.ActivityMajorRecomendationBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.main.MainActivity
import com.dicoding.visitcampus.ui.main.MainViewModel
import com.google.android.material.appbar.MaterialToolbar

class MajorRecomendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMajorRecomendationBinding

    private var currentQuestion = 0
    private val result: ArrayList<String> = arrayListOf()
    private val majorRecomendationViewModel: MajorRecomendationViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMajorRecomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val categories = resources.getStringArray(R.array.category_major_recomendation)
        val questions = resources.getStringArray(R.array.major_recomendation_question)

        Log.i("MajorRecomendationActivity", "categories: $categories")
        Log.i("MajorRecomendationActivity", "categories: $questions")

        setRegisterEnable()

        binding.tvQuestion.text = categories[currentQuestion]
        binding.tvQuestion2.text = questions[currentQuestion]
        binding.etMessageBox.doAfterTextChanged { setRegisterEnable() }
        binding.tvProgress.text = "$currentQuestion" + "/" + "${questions!!.size}"

        binding.btnCheckRecomendation.setOnClickListener {
            currentQuestion++
            binding.progressBar.progress = currentQuestion
            binding.tvProgress.text = "$currentQuestion" + "/" + "${questions!!.size}"
            Log.i("MajorRecomendationActivity", "$currentQuestion")
            if (currentQuestion == 5) {
                AlertDialog.Builder(this).apply {
                    setTitle("Failed")
                    setMessage(getString(R.string.toast_predict_failed_major_recomendation))
                    setPositiveButton(getString(R.string.try_again)) {_, _ ->
                        tryAgain()
                    }
                    create()
                    show()
                }
            }
            else if (questions.size == currentQuestion) {
                result.add(binding.etMessageBox.text.toString())
                val answers = RequestPredictBody(
                    result[0],
                    result[1],
                    result[2],
                    result[3]
                    )
                Log.i("MajorRecomendationActivity", "result: $result")
                Log.i("MajorRecomendationActivity", "answers: $answers")

                viewModel.getSession().observe(this) {user ->
                    majorRecomendationViewModel.predict(answers, user.userId)
                }
                majorRecomendationViewModel.predictResult.observe(this) { it ->
                    if (it != null) {
                        when (it) {
                            is Result.Loading -> showLoading(true)
                            is Result.Success ->
                            {
                                showLoading(true)
                                Toast.makeText(
                                    this,
                                    "Predict successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, ResultMajorRecomendationActivity::class.java)
                                intent.putExtra(ResultMajorRecomendationActivity.RESULT_RECOMENDATION, it.data)
                                startActivity(intent)
                                this@MajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            }
                            is Result.Error -> {
                                showLoading(false)
                                Toast.makeText(
                                    this,
                                    it.error,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
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
        val tvMajorRecomendation = ObjectAnimator.ofFloat(binding.tvMajorRecomendation, View.ALPHA, 1f).setDuration(1000)
        val tvText1 = ObjectAnimator.ofFloat(binding.tvQuestion, View.ALPHA, 1f).setDuration(1000)
        val tvText2 = ObjectAnimator.ofFloat(binding.tvQuestion2, View.ALPHA, 1f).setDuration(1000)
        val etMessageBox = ObjectAnimator.ofFloat(binding.etMessageBox, View.ALPHA, 1f).setDuration(1000)
        val btnCheckRecomendation = ObjectAnimator.ofFloat(binding.btnCheckRecomendation, View.ALPHA, 1f).setDuration(1000)

        AnimatorSet().apply {
            playTogether(tvMajorRecomendation, tvText1, tvText2, etMessageBox, btnCheckRecomendation)
            start()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        val progressDialog = ProgressDialog(this@MajorRecomendationActivity)
        Log.i("MajorRecomendationActivity", "isLoading: $isLoading")
        if (isLoading) {
            progressDialog.setTitle("Checking")
            progressDialog.setMessage("The system is working, please wait...")
            progressDialog.show()
        } else {
            progressDialog.hide()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        this@MajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun tryAgain() {
        val intent = Intent(this, MajorRecomendationActivity::class.java)
        startActivity(intent)
        this@MajorRecomendationActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}