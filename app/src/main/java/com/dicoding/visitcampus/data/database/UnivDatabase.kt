package com.dicoding.visitcampus.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.visitcampus.data.response.UnivItem

@Database(
    entities = [UnivItem::class],
    version = 1
)

abstract class UnivDatabase: RoomDatabase() {
    abstract fun univDao(): UnivDao


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