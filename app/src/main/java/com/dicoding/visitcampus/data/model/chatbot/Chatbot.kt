package com.dicoding.visitcampus.data.model.chatbot

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "chatbot")
@Parcelize
data class Chatbot(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val chat: String,
    val isUser: Boolean
): Parcelable