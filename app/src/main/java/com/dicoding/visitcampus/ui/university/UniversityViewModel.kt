package com.dicoding.visitcampus.ui.university

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.dicoding.visitcampus.data.VisitCampusRepository

class UniversityViewModel(private val repository: VisitCampusRepository) : ViewModel() {


    private val _keyword = MutableLiveData<String>()
    init {
        _keyword.value = ""
    }

    fun getUniversities() = _keyword.switchMap {
        repository.getUniversities(it)
    }

    fun search(keyword: String) {
        _keyword.value = keyword
    }

    fun getMajors() = repository.getMajors()

    fun filterUniversities(typeUniv: String, accreditationUniv: String, scope: String, majorName: String, accreditationMajor: String) =
        repository.filterUniversities(typeUniv, accreditationUniv, scope, majorName, accreditationMajor)

}