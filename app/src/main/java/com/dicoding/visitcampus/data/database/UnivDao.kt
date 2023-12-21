package com.dicoding.visitcampus.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.dicoding.visitcampus.data.response.MajorResponse

@Dao
interface UnivDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUniv(univ: List<UnivEntity>)

    @RawQuery(observedEntities = [UnivEntity::class])
    fun getAllUniv(query: SupportSQLiteQuery): LiveData<List<UnivEntity>>

    @Query("DELETE FROM univ")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMajor(major: List<MajorResponse>)

    @RawQuery(observedEntities = [MajorResponse::class])
    fun getAllMajor(query: SupportSQLiteQuery): LiveData<List<MajorResponse>>

    @Query("DELETE FROM major")
    fun deleteAllMajor()
}