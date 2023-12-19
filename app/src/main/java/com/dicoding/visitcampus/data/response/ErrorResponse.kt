package com.dicoding.visitcampus.data.response

import com.google.gson.annotations.SerializedName

class ErrorResponse (
    @field:SerializedName("error")
    val error: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null
)