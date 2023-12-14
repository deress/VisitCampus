package com.dicoding.visitcampus.data

import android.util.Log
import com.dicoding.visitcampus.data.api.ApiService
import com.dicoding.visitcampus.data.response.PredictResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.model.RequestPredictBody
import kotlinx.coroutines.flow.flowOn

class VisitCampusRepository(private val apiService: ApiService) {
    fun predict(requestPredictBody: RequestPredictBody): Flow<Result<PredictResponse>?> {
        return flow {
            emit(Result.Loading)
            try {
                val result = apiService.predict(requestPredictBody)
                emit(Result.Success(result))
            } catch (e: HttpException) {
                Log.d("StoryRepository", "register: ${e.message.toString()} ")
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        @Volatile
        private var instance: VisitCampusRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): VisitCampusRepository =
            instance ?: synchronized(this) {
                instance ?: VisitCampusRepository(apiService)
            }.also { instance = it }
    }
}