package com.dicoding.visitcampus.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.di.Injection
import com.dicoding.visitcampus.ui.exercise.ExamViewModel
import com.dicoding.visitcampus.ui.major.MajorRecomendationViewModel

class ViewModelFactory(private val visitCampusRepository: VisitCampusRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        MajorRecomendationViewModel::class.java -> MajorRecomendationViewModel(visitCampusRepository)
        ExamViewModel::class.java -> ExamViewModel(visitCampusRepository)
        else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    } as T

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}