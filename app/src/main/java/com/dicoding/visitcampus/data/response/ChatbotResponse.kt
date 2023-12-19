package com.dicoding.visitcampus.data.response

import com.google.gson.annotations.SerializedName

data class ChatbotResponse (
    @SerializedName("answer")
    val answer: String
)