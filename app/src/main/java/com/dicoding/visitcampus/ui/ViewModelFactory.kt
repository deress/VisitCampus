package com.dicoding.visitcampus.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.di.Injection
import com.dicoding.visitcampus.ui.home.HomeViewModel
import com.dicoding.visitcampus.ui.university.UniversityViewModel
import com.dicoding.visitcampus.ui.university.detail.DetailViewModel

class ViewModelFactory private constructor(private val repository: VisitCampusRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UniversityViewModel::class.java) -> {
                UniversityViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(): ViewModelFactory {
            return ViewModelFactory(Injection.provideRepository())
        }

    }
}