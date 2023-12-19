package com.dicoding.visitcampus.data

import android.util.Log
import com.dicoding.visitcampus.data.api.ApiService
import com.dicoding.visitcampus.data.response.PredictResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import com.dicoding.visitcampus.data.model.RequestPredictBody
import com.dicoding.visitcampus.data.model.exam.Question
import com.dicoding.visitcampus.data.response.ExamsResponse
import com.dicoding.visitcampus.data.response.ResultExamResponse
import kotlinx.coroutines.flow.flowOn

class VisitCampusRepository(private val apiService: ApiService) {
    fun predict(requestPredictBody: RequestPredictBody): Flow<Result<PredictResponse>?> {
        return flow {
            emit(Result.Loading)
            try {
                val result = apiService.predict(requestPredictBody)
                emit(Result.Success(result))
            } catch (e: HttpException) {
                Log.d("StoryRepository", "error: ${e.message.toString()} ")
            }
        }.flowOn(Dispatchers.IO)
    }

    fun exams(): Flow<Result<List<ExamsResponse>>> {
        return flow {
            emit(Result.Loading)
            try {
                val result = apiService.exams()
                emit(Result.Success(result))
            } catch (e: HttpException) {
                Log.d("StoryRepository", "error: ${e.message.toString()} ")
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getExamQuestions(id: Int): Flow<Result<List<Question>>> {
        return flow {
            emit(Result.Loading)
            try {
                val result = apiService.getExamQuestions(id)
                emit(Result.Success(result))
            } catch (e: HttpException) {
                Log.d("StoryRepository", "error: ${e.message.toString()} ")
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getResultExam(id: Int): Flow<Result<List<ResultExamResponse>>> {
        return flow {
            emit(Result.Loading)
            try {
                val result = apiService.getResultExam(id)
                emit(Result.Success(result))
            } catch (e: HttpException) {
                Log.d("StoryRepository", "error: ${e.message.toString()} ")
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