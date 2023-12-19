package com.dicoding.visitcampus.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.visitcampus.data.model.chatbot.Chatbot
import com.dicoding.visitcampus.data.model.major.MajorRecomendation
import com.dicoding.visitcampus.data.response.UnivItem

@Database(
    entities = [UnivItem::class, Chatbot::class, MajorRecomendation::class],
    version = 3,
    exportSchema = false
)

abstract class UnivDatabase: RoomDatabase() {
    abstract fun univDao(): UnivDao
    abstract fun chatbotDao(): ChatbotDao
    abstract fun majorRecomendationDao(): MajorRecomendationDao

    companion object {
        @Volatile
        private var INSTANCE: UnivDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UnivDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UnivDatabase::class.java, "univ.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}