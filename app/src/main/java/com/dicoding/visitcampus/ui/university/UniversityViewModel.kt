package com.dicoding.visitcampus.ui.university

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.model.UniversityData
import com.dicoding.visitcampus.data.response.UnivItem

class UniversityViewModel(private val repository: VisitCampusRepository) : ViewModel() {

    fun getUniversities() = repository.getUniversities()

    private val _keyword = MutableLiveData<String>()
    val univ: LiveData<List<UnivItem>> = _keyword.switchMap {
        repository.getUniversitiesDao(it)
    }

    init {
        _keyword.value = ""
    }

    fun search(keyword: String) {
        _keyword.value = keyword
    }





}