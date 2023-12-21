package com.dicoding.visitcampus.ui.major

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.request.RequestPredictBody
import com.dicoding.visitcampus.data.response.PredictResponse
import kotlinx.coroutines.launch
import com.dicoding.visitcampus.data.Result

class MajorRecomendationViewModel(private val visitCampusRepository: VisitCampusRepository):
    ViewModel() {
    private val _predictResult = MutableLiveData<Result<PredictResponse>?>()
    val predictResult: LiveData<Result<PredictResponse>?> = _predictResult

    fun predict(requestPredictBody: RequestPredictBody, userId: String) {
        viewModelScope.launch {
            visitCampusRepository.predict(requestPredictBody, userId).collect {
                _predictResult.postValue(it)
            }
        }
    }

    fun getMajorRecomendation(userId: String) = visitCampusRepository.getMajorRecomendation(userId)
}