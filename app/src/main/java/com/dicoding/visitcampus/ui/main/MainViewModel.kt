package com.dicoding.visitcampus.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.model.UniversityData
import com.dicoding.visitcampus.data.pref.UserModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: VisitCampusRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}