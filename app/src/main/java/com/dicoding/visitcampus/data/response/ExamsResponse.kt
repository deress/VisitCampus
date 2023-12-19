package com.dicoding.visitcampus.data.response

import com.google.gson.annotations.SerializedName

data class ExamsResponse(

	@field:SerializedName("practice_id")
	val practiceId: Int,

	@field:SerializedName("title")
	val title: String
)
