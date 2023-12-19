package com.dicoding.visitcampus.data.model.major

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "major_recomendation")
@Parcelize

data class MajorRecomendation (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val saintek: Float,
    val soshum: Float
): Parcelable