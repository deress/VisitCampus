package com.dicoding.visitcampus.data.model.exam

data class Question(
    val question_id: Int,
    val practice_id: Int,
    val question_text: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctOption: Int
)