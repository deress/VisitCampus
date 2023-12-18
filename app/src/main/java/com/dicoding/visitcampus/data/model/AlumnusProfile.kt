package com.dicoding.visitcampus.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlumnusProfile(
    val id: Long,
    val alumnusName: String,
    val alumnusCohort: String,
    val alumnusCareer: String,
    val alumnusPhoto: Int,
):Parcelable
