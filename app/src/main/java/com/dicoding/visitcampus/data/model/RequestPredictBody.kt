package com.dicoding.visitcampus.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestPredictBody(
    val ei_text: String,
    val sn_text: String,
    val tf_text: String,
    val jp_text: String
): Parcelable