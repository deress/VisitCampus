package com.dicoding.visitcampus.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.visitcampus.data.model.chatbot.Chatbot

@Dao
interface ChatbotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatbot(chatbot: Chatbot)
    @Query("SELECT * FROM chatbot WHERE userId = :userId")
    fun getChatbot(userId: String): LiveData<List<Chatbot>>
}