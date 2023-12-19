package com.dicoding.visitcampus.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.visitcampus.data.model.major.MajorRecomendation

@Dao
interface MajorRecomendationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMajorRecomendation(majorRecomendation: MajorRecomendation)
    @Query("SELECT * FROM major_recomendation WHERE userId = :userId ORDER BY id DESC LIMIT 1")
    fun getMajorRecomendation(userId: String): LiveData<MajorRecomendation>
}