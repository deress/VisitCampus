package com.dicoding.visitcampus.di

import android.content.Context
import com.dicoding.visitcampus.data.VisitCampusRepository
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(): VisitCampusRepository {
        return VisitCampusRepository.getInstance()
    }
}