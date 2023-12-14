package com.dicoding.visitcampus.di

import android.content.Context
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): VisitCampusRepository {
        val apiService = ApiConfig.getApiService()
        return VisitCampusRepository.getInstance(apiService)
    }
}