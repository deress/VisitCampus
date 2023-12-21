package com.dicoding.visitcampus.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "univ")
class UnivEntity(
    @field:ColumnInfo("id")
    @field:PrimaryKey
    val universityId: Int,

    @field:ColumnInfo("univName")
    val univName: String,

    @field:ColumnInfo("univDescription")
    val univDescription: String,

    @field:ColumnInfo("logoPhoto")
    val univLogo: String,

    @field:ColumnInfo("coverPhoto")
    val univCover: String,

    @field:ColumnInfo("latitude")
    val latitude: Double,

    @field:ColumnInfo("longitude")
    val longitude: Double,
)