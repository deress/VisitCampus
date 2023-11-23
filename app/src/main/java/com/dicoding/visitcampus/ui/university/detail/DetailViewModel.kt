package com.dicoding.visitcampus.ui.university.detail

import androidx.lifecycle.ViewModel
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.model.University
import com.dicoding.visitcampus.data.model.UniversityData

class DetailViewModel(private val repository: VisitCampusRepository) : ViewModel() {
    fun getUnivById(name : String): University = UniversityData.dummyUniversity.first {
            it.univName == name
    }


}