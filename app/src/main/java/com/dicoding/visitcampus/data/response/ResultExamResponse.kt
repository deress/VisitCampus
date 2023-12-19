package com.dicoding.visitcampus.data.response

import com.google.gson.annotations.SerializedName
data class ResultExamResponse(

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("answer")
	val answer: String,

	@field:SerializedName("result_id")
	val resultId: Int,

	@field:SerializedName("practice_id")
	val practiceId: Int,

	@field:SerializedName("explanation_description")
	val explanationDescription: String
)
