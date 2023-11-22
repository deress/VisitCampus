package com.dicoding.visitcampus.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chatbot(
    val id: Int?,
    val chat: String,
    val isUser: Boolean
): Parcelable