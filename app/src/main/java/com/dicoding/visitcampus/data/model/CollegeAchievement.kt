package com.dicoding.visitcampus.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CollegeAchievement (
    val id: Long,
    val achievementName: String,
    val achievementDate: String
): Parcelable

