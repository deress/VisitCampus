package com.dicoding.visitcampus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.model.UniversityData
import com.dicoding.visitcampus.data.response.UnivItem

class HomeViewModel(private val repository: VisitCampusRepository) : ViewModel() {

    val carouselPhoto = UniversityData.dummyCarousel

    fun getUniversities() = repository.getUniversities()

    val univ: LiveData<List<UnivItem>> = repository.getUniversitiesDao(keyword = "")

}