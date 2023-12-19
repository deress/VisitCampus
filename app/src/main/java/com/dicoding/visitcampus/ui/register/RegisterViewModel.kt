package com.dicoding.visitcampus.ui.register

import androidx.lifecycle.ViewModel
import com.dicoding.visitcampus.data.VisitCampusRepository

class RegisterViewModel(private val repository: VisitCampusRepository): ViewModel() {
    fun postRegister(name: String, email: String, password: String) = repository.postRegister(name, email, password)

}