package com.dicoding.visitcampus.data.api

import com.dicoding.visitcampus.data.model.RequestPredictBody
import com.dicoding.visitcampus.data.response.PredictResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("predict")
    suspend fun predict(
        @Body requestPredictBody: RequestPredictBody
    ): PredictResponse
}