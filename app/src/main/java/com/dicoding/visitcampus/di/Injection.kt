package com.dicoding.visitcampus.di

import android.content.Context
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.api.ApiConfig
import com.dicoding.visitcampus.data.database.UnivDatabase
import com.dicoding.visitcampus.data.pref.UserPreference
import com.dicoding.visitcampus.data.pref.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): VisitCampusRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService()
        val loginService = ApiConfig.signService()
        val database = UnivDatabase.getDatabase(context)




        return VisitCampusRepository.getInstance(pref, apiService, loginService, database)
    }
}