package com.dicoding.visitcampus.ui.university

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.model.University
import com.dicoding.visitcampus.data.model.UniversityData

class UniversityViewModel(private val repository: VisitCampusRepository) : ViewModel() {
    val university = UniversityData.dummyUniversity

    fun getUniversities() = repository.getUniversities()
}