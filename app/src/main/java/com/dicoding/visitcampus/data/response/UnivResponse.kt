package com.dicoding.visitcampus.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class UnivResponse(

	@field:SerializedName("univ")
	val univ: List<UnivItem>
)


@Entity(tableName = "univ")
data class UnivItem(

	@field:SerializedName("logoPhoto")
	val logoPhoto: String,

	@field:SerializedName("univDescription")
	val univDescription: String,

	@field:SerializedName("univName")
	val univName: String,

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("coverPhoto")
	val coverPhoto: String,

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("longitude")
	val longitude: Double,
)

