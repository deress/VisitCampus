package com.dicoding.visitcampus.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiConfig {
    fun getApiService(): ApiService {

        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://visitcampus.free.beeceptor.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    fun signService(): LoginService{

        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://story-api.dicoding.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(LoginService::class.java)
    }



}

//    val authInterceptor = Interceptor { chain ->
//        val req = chain.request()
//        val requestHeaders = req.newBuilder()
//            .addHeader("Authorization", "Bearer $token")
//            .build()
//        chain.proceed(requestHeaders)
//    }

//    val loggingInterceptor = if(BuildConfig.DEBUG) {
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    } else {
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//    }