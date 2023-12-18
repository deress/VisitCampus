package com.dicoding.visitcampus.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.pref.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: VisitCampusRepository): ViewModel() {

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun postLogin(email: String, password:String) = repository.postLogin(email,password)

}