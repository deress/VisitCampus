package com.dicoding.visitcampus.data


import android.os.Build
import android.os.ext.SdkExtensions
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.visitcampus.data.api.ApiConfig
import com.dicoding.visitcampus.data.api.ApiService
import com.dicoding.visitcampus.data.api.ExamService
import com.dicoding.visitcampus.data.api.LoginService
import com.dicoding.visitcampus.data.database.UnivDao
import com.dicoding.visitcampus.data.database.UnivEntity
import com.dicoding.visitcampus.data.model.RequestPredictBody
import com.dicoding.visitcampus.data.model.exam.Question
import com.dicoding.visitcampus.data.pref.UserModel
import com.dicoding.visitcampus.data.pref.UserPreference
import com.dicoding.visitcampus.data.response.ErrorResponse
import com.dicoding.visitcampus.data.response.ExamsResponse
import com.dicoding.visitcampus.data.response.MajorResponse
import com.dicoding.visitcampus.data.response.PredictResponse
import com.dicoding.visitcampus.data.response.ResultExamResponse
import com.dicoding.visitcampus.data.response.UniversitiesResponseItem
import com.dicoding.visitcampus.util.AppExecutors
import com.dicoding.visitcampus.util.SearchUtils
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


class VisitCampusRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    private val loginService: LoginService,
    private val examService: ExamService,
    private val univDao: UnivDao,
    private val appExecutors: AppExecutors


) {
    private val result = MediatorLiveData<Result<List<UnivEntity>>>()
    private val resultMajor = MediatorLiveData<Result<List<MajorResponse>>>()
    private val resultFilterUniv = MediatorLiveData<Result<List<UnivEntity>>>()

    fun predict(requestPredictBody: RequestPredictBody): Flow<Result<PredictResponse>?> {
        return flow {
            emit(Result.Loading)
            try {
                val result = examService.predict(requestPredictBody)
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
                val result = examService.exams()
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
                val result = examService.getExamQuestions(id)
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
                val result = examService.getResultExam(id)
                emit(Result.Success(result))
            } catch (e: HttpException) {
                Log.d("StoryRepository", "error: ${e.message.toString()} ")
            }
        }.flowOn(Dispatchers.IO)
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

    fun getUniversities(keyword: String): LiveData<Result<List<UnivEntity>>> {
        result.value = Result.Loading
        val client = apiService.getUniversities()
        client.enqueue(object: Callback<List<UniversitiesResponseItem>> {
            override fun onResponse(call: Call<List<UniversitiesResponseItem>>, response: Response<List<UniversitiesResponseItem>>) {
                if (response.isSuccessful) {
                    val dataUniv = response.body()
                    val univList = ArrayList<UnivEntity>()
                    appExecutors.diskIO.execute {
                        dataUniv?.forEach { univ ->
                            val univs = UnivEntity(
                                univ.universityId,
                                univ.univName,
                                univ.personalityUniv,
                                univ.univLogo,
                                univ.univCover,
                                univ.latitude,
                                univ.longitude
                            )
                            univList.add(univs)
                        }
                        univDao.deleteAll()
                        univDao.insertUniv(univList)
                    }
                }
            }
            override fun onFailure(call: Call<List<UniversitiesResponseItem>>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }
        })

        val query = SearchUtils.getSearchQuery(keyword)

        val localData = univDao.getAllUniv(query)
        result.addSource(localData) { univData: List<UnivEntity> ->
            result.value = Result.Success(univData)
        }
        return result
    }

    fun getMajors(): LiveData<Result<List<MajorResponse>>> {
        resultMajor.value = Result.Loading
        val client = apiService.getMajors()
        client.enqueue(object: Callback<List<MajorResponse>> {
            override fun onResponse(call: Call<List<MajorResponse>>, response: Response<List<MajorResponse>>) {
                if (response.isSuccessful) {
                    val dataMajor = response.body()
                    val majorList = ArrayList<MajorResponse>()
                    appExecutors.diskIO.execute {
                        dataMajor?.forEach { major ->
                            val majors = MajorResponse(
                                major.facultyId,
                                major.majorName,
                                major.accreditationMajor,
                                major.majorId
                            )
                            majorList.add(majors)
                        }
                        univDao.deleteAllMajor()
                        univDao.insertMajor(majorList)
                    }
                }
            }
            override fun onFailure(call: Call<List<MajorResponse>>, t: Throwable) {
                resultMajor.value = Result.Error(t.message.toString())
            }
        })

        val query = SimpleSQLiteQuery("SELECT * FROM major")
        val localData = univDao.getAllMajor(query)
        resultMajor.addSource(localData) { majorData: List<MajorResponse> ->
            resultMajor.value = Result.Success(majorData)
        }
        return resultMajor
    }

    fun filterUniversities(typeUniv: String, accreditationUniv: String, scope: String, majorName: String, accreditationMajor: String)
    : LiveData<Result<List<UnivEntity>>> {
        resultFilterUniv.value = Result.Loading
        val client = ApiConfig.getApiService().filterUniversities(
            typeUniv = typeUniv,
            accreditationUniv = accreditationUniv,
            scope = scope,
            majorName = majorName,
            accreditationMajor = accreditationMajor
        )
        client.enqueue(object : Callback<List<UniversitiesResponseItem>> {
            override fun onResponse(call: Call<List<UniversitiesResponseItem>>, response: Response<List<UniversitiesResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val dataUniv = response.body()
                    val univList = ArrayList<UnivEntity>()
                    appExecutors.diskIO.execute {
                        dataUniv?.forEach { univ ->
                            val univs = UnivEntity(
                                univ.universityId,
                                univ.univName,
                                univ.personalityUniv,
                                univ.univLogo,
                                univ.univCover,
                                univ.latitude,
                                univ.longitude
                            )
                            univList.add(univs)
                        }
                        univDao.deleteAll()
                        univDao.insertUniv(univList)
                    }
                } else {
                    resultFilterUniv.value = Result.Error(response.message().toString())
                    appExecutors.diskIO.execute {
                        univDao.deleteAll()
                    }
                }
            }
            override fun onFailure(call: Call<List<UniversitiesResponseItem>>, t: Throwable) {
                resultFilterUniv.value = Result.Error(t.message.toString())
            }
        })

        val query = SearchUtils.getSearchQuery("")

        val localData = univDao.getAllUniv(query)
        resultFilterUniv.addSource(localData) { univData: List<UnivEntity> ->
            resultFilterUniv.value = Result.Success(univData)
        }
        return resultFilterUniv
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
            univDao: UnivDao,
            appExecutors: AppExecutors

        ): VisitCampusRepository = VisitCampusRepository(userPreference, apiService, loginService, examService, univDao, appExecutors)

    }
}