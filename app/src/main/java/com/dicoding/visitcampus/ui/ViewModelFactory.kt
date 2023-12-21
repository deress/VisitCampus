package com.dicoding.visitcampus.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.di.Injection
import com.dicoding.visitcampus.ui.chatbot.ChatbotViewModel
import com.dicoding.visitcampus.ui.home.HomeViewModel
import com.dicoding.visitcampus.ui.login.LoginViewModel
import com.dicoding.visitcampus.ui.main.MainViewModel
import com.dicoding.visitcampus.ui.register.RegisterViewModel
import com.dicoding.visitcampus.ui.university.UniversityViewModel
import com.dicoding.visitcampus.ui.university.detail.DetailViewModel
import com.dicoding.visitcampus.ui.exercise.ExamViewModel
import com.dicoding.visitcampus.ui.major.MajorRecomendationViewModel




class ViewModelFactory private constructor(private val repository: VisitCampusRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(UniversityViewModel::class.java) -> {
                UniversityViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MajorRecomendationViewModel::class.java) -> {
                MajorRecomendationViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ExamViewModel::class.java) -> {
                ExamViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ChatbotViewModel::class.java) -> {
                ChatbotViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            return ViewModelFactory(Injection.provideRepository(context))
        }
    }
}