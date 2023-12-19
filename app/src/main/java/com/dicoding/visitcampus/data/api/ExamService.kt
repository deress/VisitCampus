package com.dicoding.visitcampus.data.api

import com.dicoding.visitcampus.data.request.RequestChatbotBody
import com.dicoding.visitcampus.data.request.RequestPredictBody
import com.dicoding.visitcampus.data.model.exam.Question
import com.dicoding.visitcampus.data.response.ChatbotResponse
import com.dicoding.visitcampus.data.response.ExamsResponse
import com.dicoding.visitcampus.data.response.PredictResponse
import com.dicoding.visitcampus.data.response.ResultExamResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ExamService {
    @POST("predict")
    suspend fun predict(
        @Body requestPredictBody: RequestPredictBody
    ): PredictResponse

    @GET("exams")
    suspend fun exams(): List<ExamsResponse>

    @GET("exams/{practiceId}/questions")
    suspend fun getExamQuestions(
        @Path("practiceId") practiceId: Int
    ): List<Question>

    @GET("exams/{practiceId}/result")
    suspend fun getResultExam(
        @Path("practiceId") practiceId: Int
    ): List<ResultExamResponse>

    @POST("chatbot")
    suspend fun chatbot(
        @Body requestChatbotBody: RequestChatbotBody
    ): ChatbotResponse
}