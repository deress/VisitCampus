package com.dicoding.visitcampus.data.api

import com.dicoding.visitcampus.data.request.RequestChatbotBody
import com.dicoding.visitcampus.data.request.RequestPredictBody
import com.dicoding.visitcampus.data.response.ChatbotResponse
import com.dicoding.visitcampus.data.response.PredictResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ModelService {
    @POST("chatbot")
    suspend fun chatbot(
        @Body requestChatbotBody: RequestChatbotBody
    ): ChatbotResponse

    @POST("predict")
    suspend fun predict(
        @Body requestPredictBody: RequestPredictBody
    ): PredictResponse
}