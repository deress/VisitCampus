package com.dicoding.visitcampus.ui.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.model.exam.Exam
import com.dicoding.visitcampus.data.model.exam.ExamData
import com.dicoding.visitcampus.data.model.exam.Explanation
import com.dicoding.visitcampus.data.model.exam.ExplanationData
import com.dicoding.visitcampus.data.response.ResultExamResponse
import com.dicoding.visitcampus.databinding.ActivityResultQuestionBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.main.MainActivity

class ResultQuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultQuestionBinding

    private val examViewModel: ExamViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvExplanation.layoutManager = layoutManager

        val totalCorrect = intent.getIntExtra(EXTRA_SCORE, 0)
        val totalQuestion = intent.getIntExtra(TOTAL_QUESTION, 0)
        val practiceId = intent.getIntExtra(PRACTICE_ID, 0)
        val score = (100/totalQuestion) * totalCorrect
        val totalFalse = totalQuestion - totalCorrect

        binding.score.text = score.toString()
        binding.falseScore.text = getString(R.string.false_question, totalFalse)
        binding.correctScor.text = getString(R.string.true_question, totalCorrect)

        examViewModel.getResultExam(practiceId)
        examViewModel.resultExam.observe(this) {
            if (it != null) {
                when(it) {
                    is Result.Loading -> showLoading(true)
                    is Result.Success -> {
                        showLoading(false)
                        val response = it.data
                        showExplanationList(response)
                    }
                    is Result.Error -> showLoading(false)
                }
            }
        }

        binding.btnFinishExam.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            this@ResultQuestionActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    private fun showExplanationList(items: List<ResultExamResponse>) {
        Log.i("ExamListFragment", "examList: $items")
        val adapter = ExplanationListAdapter()
        adapter.submitList(items)
        binding.rvExplanation.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        this@ResultQuestionActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    companion object{
        const val EXTRA_SCORE = "extra_score"
        const val TOTAL_QUESTION = "total_question"
        const val PRACTICE_ID = "practice_id"
    }
}