package com.dicoding.visitcampus.data.model.exam

data class Explanation(
    val id: Int,
    val examId: Int,
    val question: String,
    val answer: String,
    val explanationDescription: String
)