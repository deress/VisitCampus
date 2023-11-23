package com.dicoding.visitcampus.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Faculty (
    val facultyName: String,
    val major: List<Major>,
) : Parcelable