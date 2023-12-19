package com.dicoding.visitcampus.ui.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.model.exam.Question
import com.dicoding.visitcampus.data.response.ExamsResponse
import com.dicoding.visitcampus.data.response.ResultExamResponse
import kotlinx.coroutines.launch

class ExamViewModel(private val visitCampusRepository: VisitCampusRepository): ViewModel() {
    private val _examListResult = MutableLiveData<Result<List<ExamsResponse>>>()
    val examListResult: LiveData<Result<List<ExamsResponse>>> = _examListResult

    private val _examQuestionResult = MutableLiveData<Result<List<Question>>>()
    val examQuestionResult: LiveData<Result<List<Question>>> = _examQuestionResult

    private val _resultExam = MutableLiveData<Result<List<ResultExamResponse>>>()
    val resultExam: LiveData<Result<List<ResultExamResponse>>> = _resultExam

    fun exams() {
        viewModelScope.launch {
            visitCampusRepository.exams().collect() {
                _examListResult.postValue(it)
            }
        }
    }

    fun getExamQuestions(id: Int) {
        viewModelScope.launch {
            visitCampusRepository.getExamQuestions(id).collect() {
                _examQuestionResult.postValue(it)
            }
        }
    }

    fun getResultExam(id: Int) {
        viewModelScope.launch {
            visitCampusRepository.getResultExam(id).collect() {
                _resultExam.postValue(it)
            }
        }
    }
}