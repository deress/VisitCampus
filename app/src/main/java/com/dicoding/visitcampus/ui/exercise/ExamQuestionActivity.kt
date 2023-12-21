package com.dicoding.visitcampus.ui.exercise

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.model.exam.Question
import com.dicoding.visitcampus.data.model.exam.QuestionData
import com.dicoding.visitcampus.databinding.ActivityExamQuestionBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.main.MainActivity
import com.dicoding.visitcampus.ui.major.MajorRecomendationViewModel
import kotlin.properties.Delegates

class ExamQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityExamQuestionBinding

    private val examViewModel: ExamViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var correctTotal: Int = 0
    private var practiceId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        practiceId = intent.getIntExtra(PRACTICE_ID, 0)
        Log.i("ExamQuestionActivity", "response: $practiceId")

        examViewModel.getExamQuestions(practiceId)
        examViewModel.examQuestionResult.observe(this) {
            Log.i("ExamQuestionActivity", "it: $it")
            if (it != null) {
                when (it) {
                    is Result.Loading -> showLoading(true)
                    is Result.Success ->
                    {
                        showLoading(false)
                        val response = it.data
                        Log.i("ExamQuestionActivity", "response: $response")
                        mQuestionList = ArrayList(response)
                        setQuestion()
                    }
                    is Result.Error -> {
                        showLoading(false)
                    }
                }
            }
        }

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun setQuestion() {

        val question = mQuestionList!!.get(mCurrentPosition - 1)

        defaultOptionsView()
        Log.i("ExamQuestionActivity", "mSelectedOptionPosition: $mSelectedOptionPosition")
        checkAnswer()

        if (mCurrentPosition == mQuestionList!!.size) {
            binding.btnSubmit.text = getString(R.string.finish)
        } else {
            binding.btnSubmit.text = getString(R.string.submit)
        }

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition" + "/" + "${mQuestionList!!.size}"

        binding.tvQuestion.text = question.question_text
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour
        mSelectedOptionPosition = 0
    }

    private fun checkAnswer() {
        if (mCurrentPosition >= 2) {
            val questionCheck = mQuestionList!!.get(mCurrentPosition - 2)
            if (questionCheck!!.correctOption == mSelectedOptionPosition) {
                answerView(mSelectedOptionPosition, R.drawable.default_option_border)
                correctTotal++
                Log.i("ExamQuestionActivity", "True")
            } else {
                answerView(mSelectedOptionPosition, R.drawable.default_option_border)
            }
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border
            )
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(binding.tvOptionOne, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(binding.tvOptionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(binding.tvOptionThree, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(binding.tvOptionFour, 4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    Toast.makeText(this, "Please select Answer!", Toast.LENGTH_SHORT).show()
                } else {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                            Log.i("ExamQuestionActivity", "correctTotal: $correctTotal")
                        }
                        else -> {
                            checkAnswer()
                            Log.i("ExamQuestionActivity", "correctTotal: $correctTotal")
                            Toast.makeText(
                                this,
                                getString(R.string.toast_exam_completed), Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, ResultQuestionActivity::class.java)
                            intent.putExtra(ResultQuestionActivity.EXTRA_SCORE, correctTotal)
                            intent.putExtra(ResultQuestionActivity.TOTAL_QUESTION, mQuestionList!!.size)
                            intent.putExtra(ResultQuestionActivity.PRACTICE_ID, practiceId)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.correct_option_border
        )
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val PRACTICE_ID = "practice_id"
    }
}