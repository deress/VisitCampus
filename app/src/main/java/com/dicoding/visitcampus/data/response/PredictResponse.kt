package com.dicoding.visitcampus.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictResponse(
	@field:SerializedName("soshum")
	val soshum: Float,

	@field:SerializedName("saintek")
	val saintek: Float
): Parcelable
