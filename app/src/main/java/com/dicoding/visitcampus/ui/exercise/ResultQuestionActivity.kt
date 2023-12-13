package com.dicoding.visitcampus.ui.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.model.exam.Exam
import com.dicoding.visitcampus.data.model.exam.ExamData
import com.dicoding.visitcampus.data.model.exam.Explanation
import com.dicoding.visitcampus.data.model.exam.ExplanationData
import com.dicoding.visitcampus.databinding.ActivityResultQuestionBinding

class ResultQuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val totalCorrect = intent.getIntExtra(EXTRA_SCORE, 0)
        val totalQuestion = intent.getIntExtra(TOTAL_QUESTION, 0)
        val score = (100/totalQuestion) * totalCorrect
        val totalFalse = totalQuestion - totalCorrect

        binding.score.text = score.toString()
        binding.falseScore.text = totalFalse.toString()
        binding.correctScor.text = totalCorrect.toString()

        val layoutManager = LinearLayoutManager(this)
        binding.rvExplanation.layoutManager = layoutManager

        showExplanationList(ExplanationData.explanationList)
    }

    private fun showExplanationList(items: List<Explanation>) {
        Log.i("ExamListFragment", "examList: $items")
        val adapter = ExplanationListAdapter()
        adapter.submitList(items)
        binding.rvExplanation.adapter = adapter
    }

    companion object{
        const val EXTRA_SCORE = "extra_score"
        const val TOTAL_QUESTION = "total_question"
    }
}