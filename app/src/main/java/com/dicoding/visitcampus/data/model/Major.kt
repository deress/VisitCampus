package com.dicoding.visitcampus.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Major(
    val majorName: String,
    val majorDegree: String,
    val majorAccreditation: String,
) : Parcelable
