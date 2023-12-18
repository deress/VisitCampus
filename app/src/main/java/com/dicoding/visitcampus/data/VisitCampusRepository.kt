package com.dicoding.visitcampus.data


import android.net.http.HttpException
import android.os.Build
import android.os.ext.SdkExtensions
import androidx.lifecycle.liveData
import com.dicoding.visitcampus.data.api.ApiService
import com.dicoding.visitcampus.data.api.LoginService
import com.dicoding.visitcampus.data.model.UniversityData
import com.dicoding.visitcampus.data.pref.UserModel
import com.dicoding.visitcampus.data.pref.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow

class VisitCampusRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    private val loginService: LoginService
) {
    private val listUniversity = UniversityData.dummyUniversity

    fun postRegister(name: String, email: String, password: String) = liveData {
        emit(Result.Loading)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.S) >= 7) {
            try {
                val successResponse = loginService.postRegister(name, email, password)
                emit(Result.Success(successResponse))
            } catch (e: HttpException) {
                emit(Result.Error("error"))
            }
        }
    }

    fun postLogin(email: String, password: String) = liveData {
        emit(Result.Loading)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.S) >= 7) {
            try {
                val successResponse = loginService.postLogin(email, password)
                emit(Result.Success(successResponse))
            } catch (e: HttpException) {
                emit(Result.Error("error"))
            }
        }
    }

    fun getUniversities() = liveData {
        emit(Result.Loading)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.TIRAMISU) >= 7) {
            try {
                val successResponse = apiService.getUniversities()
                emit(Result.Success(successResponse))
            } catch (e: HttpException) {
                emit(Result.Error("error"))
            }
        }
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
            loginService: LoginService
        ): VisitCampusRepository = VisitCampusRepository(userPreference, apiService, loginService)
    }
}