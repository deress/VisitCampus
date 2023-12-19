package com.dicoding.visitcampus.ui.university.detail

import androidx.lifecycle.ViewModel
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.model.UniversityData

class DetailViewModel(private val repository: VisitCampusRepository) : ViewModel() {

    fun getUniv(id: Int) = repository.getUniv(id)


}