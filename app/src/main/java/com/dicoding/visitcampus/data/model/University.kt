package com.dicoding.visitcampus.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class University(
    val id: Long,
    val univName: String,
    val univDescription: String,
    val logoPhoto: Int,
    val coverPhoto: Int,
    val latitude: Double,
    val longtitude: Double,
): Parcelable
