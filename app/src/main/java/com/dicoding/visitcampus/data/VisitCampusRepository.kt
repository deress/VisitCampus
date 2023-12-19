package com.dicoding.visitcampus.data



import android.os.Build
import android.os.ext.SdkExtensions
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.visitcampus.data.api.ApiService
import com.dicoding.visitcampus.data.api.LoginService
import com.dicoding.visitcampus.data.database.UnivDatabase
import com.dicoding.visitcampus.data.pref.UserModel
import com.dicoding.visitcampus.data.pref.UserPreference
import com.dicoding.visitcampus.data.response.ErrorResponse
import com.dicoding.visitcampus.data.response.UnivItem
import com.dicoding.visitcampus.util.SearchUtils
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException


class VisitCampusRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    private val loginService: LoginService,
    private val univDatabase: UnivDatabase,

) {
    init {
        getUniversities()
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
            univDatabase: UnivDatabase,

        ): VisitCampusRepository = VisitCampusRepository(userPreference, apiService, loginService, univDatabase)
    }
}