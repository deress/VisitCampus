package com.dicoding.visitcampus.data.model.exam

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exam(
    val id: Int,
    val examName: String,
): Parcelable