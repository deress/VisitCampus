package com.dicoding.visitcampus.data.api
import com.dicoding.visitcampus.data.response.MajorResponse
import com.dicoding.visitcampus.data.response.UniversitiesResponseItem
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("universities")
    fun getUniversities(
    ): Call<List<UniversitiesResponseItem>>

    @GET("universities/{id}")
    suspend fun getDetailUniv(
        @Path("id") id: Int
    ): UniversitiesResponseItem

    @GET("majors")
    fun getMajors(
    ): Call<List<MajorResponse>>

    @GET("filter-univ")
    fun filterUniversities(
        @Query("type_univ") typeUniv: String,
        @Query("accreditation_univ") accreditationUniv: String,
        @Query("scope") scope: String,
        @Query("major_name") majorName: String,
        @Query("accreditation_major") accreditationMajor: String
    ): Call<List<UniversitiesResponseItem>>

}