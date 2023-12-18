package com.dicoding.visitcampus.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class University(
    val id: Long,
    val univName: String,
    val univDescription: String,
    val logoPhoto: String,
    val coverPhoto: String,
    val latitude: Double,
    val longitude: Double,
    val collegeAchievement: List<CollegeAchievement>,
    val alumnusProfile: List<AlumnusProfile>,
    val registrationPath: List<RegistrationPath>,
    val faculty: List<Faculty>,

): Parcelable
