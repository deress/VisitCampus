package com.dicoding.visitcampus.data.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RequestChatbotBody (
    val question: String
): Parcelable