package com.dicoding.visitcampus.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.dicoding.visitcampus.data.response.UnivItem

@Dao
interface UnivDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniv(univ: List<UnivItem>)

    @RawQuery(observedEntities = [UnivItem::class])
    fun getAllUniv(query: SupportSQLiteQuery): LiveData<List<UnivItem>>

    @Query("DELETE FROM univ")
    suspend fun deleteAll()
}