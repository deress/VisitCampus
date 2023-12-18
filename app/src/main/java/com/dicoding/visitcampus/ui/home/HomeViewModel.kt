package com.dicoding.visitcampus.ui.home

import androidx.lifecycle.ViewModel
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.model.UniversityData

class HomeViewModel(private val repository: VisitCampusRepository) : ViewModel() {
    val university = UniversityData.dummyUniversity

    val carouselPhoto = UniversityData.dummyCarousel

    fun getUniversities() = repository.getUniversities()

}