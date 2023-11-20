package com.dicoding.visitcampus.data



import android.net.http.HttpException
import android.os.Build
import android.os.ext.SdkExtensions
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.data.model.University
import com.dicoding.visitcampus.data.model.UniversityData

class VisitCampusRepository {
    private val listUniversity = UniversityData.dummyUniversity

//    fun getUniversities() = liveData {
//        emit(Result.Loading)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
//                Build.VERSION_CODES.S) >= 7) {
//            try {
//                val successResponse = listUniversity
//                emit(Result.Success(successResponse))
//            } catch (e: HttpException) {
//                emit(Result.Error("error"))
//            }
//        }
//    }

    companion object {
        @Volatile
        private var instance: VisitCampusRepository? = null

        fun getInstance(): VisitCampusRepository =
            instance ?: synchronized(this) {
                VisitCampusRepository().apply {
                    instance = this
                }
            }
    }
}