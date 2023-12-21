package com.dicoding.visitcampus.data.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestPredictBody(
    val EI_text: String,
    val SN_text: String,
    val TF_text: String,
    val JP_text: String
): Parcelable