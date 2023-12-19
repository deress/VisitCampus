package com.dicoding.visitcampus.data


import android.util.Log
import com.dicoding.visitcampus.data.api.ApiService
import com.dicoding.visitcampus.data.response.PredictResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import com.dicoding.visitcampus.data.request.RequestPredictBody
import com.dicoding.visitcampus.data.model.exam.Question
import com.dicoding.visitcampus.data.response.ExamsResponse
import com.dicoding.visitcampus.data.response.ResultExamResponse
import kotlinx.coroutines.flow.flowOn
import android.os.Build
import android.os.ext.SdkExtensions
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.visitcampus.data.api.ExamService
import com.dicoding.visitcampus.data.api.LoginService
import com.dicoding.visitcampus.data.database.UnivDatabase
import com.dicoding.visitcampus.data.model.chatbot.Chatbot
import com.dicoding.visitcampus.data.model.major.MajorRecomendation
import com.dicoding.visitcampus.data.request.RequestChatbotBody
import com.dicoding.visitcampus.data.pref.UserModel
import com.dicoding.visitcampus.data.pref.UserPreference
import com.dicoding.visitcampus.data.response.ChatbotResponse
import com.dicoding.visitcampus.data.response.ErrorResponse
import com.dicoding.visitcampus.data.response.UnivItem
import com.dicoding.visitcampus.util.SearchUtils
import com.google.gson.Gson


class VisitCampusRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    private val loginService: LoginService,
    private val examService: ExamService,
    private val univDatabase: UnivDatabase,

) {
    init {
        getUniversities()
    }

    fun predict(requestPredictBody: RequestPredictBody, userId: String): Flow<Result<PredictResponse>?> {
        return flow {
            emit(Result.Loading)
            try {
                val result = examService.predict(requestPredictBody)
                val resultPredict = MajorRecomendation(userId = userId, saintek = result.saintek, soshum = result.soshum)
                univDatabase.majorRecomendationDao().insertMajorRecomendation(resultPredict)
                emit(Result.Success(result))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                emit(Result.Error(errorBody.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMajorRecomendation(userId: String): LiveData<MajorRecomendation> {
        return univDatabase.majorRecomendationDao().getMajorRecomendation(userId)
    }

    fun exams(): Flow<Result<List<ExamsResponse>>> {
        return flow {
            emit(Result.Loading)
            try {
                val result = examService.exams()
                emit(Result.Success(result))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                emit(Result.Error(errorBody.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getExamQuestions(id: Int): Flow<Result<List<Question>>> {
        return flow {
            emit(Result.Loading)
            try {
                val result = examService.getExamQuestions(id)
                emit(Result.Success(result))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                emit(Result.Error(errorBody.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getResultExam(id: Int): Flow<Result<List<ResultExamResponse>>> {
        return flow {
            emit(Result.Loading)
            try {
                val result = examService.getResultExam(id)
                emit(Result.Success(result))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                emit(Result.Error(errorBody.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun chatbot(userId: String, requestChatbotBody: RequestChatbotBody): LiveData<Result<ChatbotResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = examService.chatbot(requestChatbotBody)
            Log.i("VisitCampusRepository", "response: $response")
            univDatabase.chatbotDao().insertChatbot(Chatbot(userId = userId, chat = requestChatbotBody.question, isUser = true))
            univDatabase.chatbotDao().insertChatbot(Chatbot(userId = userId, chat = response.answer, isUser = false))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            emit(Result.Error(errorBody.message.toString()))
        }
    }

    fun getChatbot(userId: String): LiveData<List<Chatbot>> {
        return univDatabase.chatbotDao().getChatbot(userId)
    }

    fun postRegister(name: String, email: String, password: String) = liveData {
        emit(Result.Loading)
        try {
            val successResponse = loginService.postRegister(name, email, password)
            emit(Result.Success(successResponse))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            emit(Result.Error(errorBody.message.toString()))
        }
    }

    fun postLogin(email: String, password: String) = liveData {
        emit(Result.Loading)
        try {
            val successResponse = loginService.postLogin(email, password)
            emit(Result.Success(successResponse))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            emit(Result.Error(errorBody.message.toString()))
        }
    }

    fun getUniversities() = liveData {
        emit(Result.Loading)
        try {
            val successResponse = apiService.getUniversities()
            univDatabase.univDao().insertUniv(successResponse.univ)
            emit(Result.Success(successResponse))
        } catch (e: HttpException) {
            emit(Result.Error("error"))
        }
    }

    fun getUniversitiesDao(keyword: String) : LiveData<List<UnivItem>> {
        val query = SearchUtils.getSearchQuery(keyword)
        return univDatabase.univDao().getAllUniv(query)
    }


    fun getUniv(id: Int) = liveData {
        emit(Result.Loading)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.TIRAMISU) >= 7) {
            try {
                val successResponse = apiService.getDetailUniv(id)
                emit(Result.Success(successResponse))
            } catch (e: HttpException) {
                emit(Result.Error("error"))
            }
        }
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: VisitCampusRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService,
            loginService: LoginService,
            examService: ExamService,
            univDatabase: UnivDatabase,

        ): VisitCampusRepository = VisitCampusRepository(userPreference, apiService, loginService, examService, univDatabase)

    }
}