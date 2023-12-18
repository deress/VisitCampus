package com.dicoding.visitcampus.data.api


import com.dicoding.visitcampus.data.response.DetailUnivResponse
import com.dicoding.visitcampus.data.response.LoginResponse
import com.dicoding.visitcampus.data.response.RegisterResponse
import com.dicoding.visitcampus.data.response.UnivResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("univ")
    suspend fun getUniversities(
    ): UnivResponse

    @GET("univ/{id}")
    suspend fun getDetailUniv(
        @Path("id") id: Int
    ): DetailUnivResponse


}