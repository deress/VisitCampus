package com.dicoding.visitcampus.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegistrationPath (
    val id: Long,
    val pathName: String,
    val pathDescription: String,
) : Parcelable

