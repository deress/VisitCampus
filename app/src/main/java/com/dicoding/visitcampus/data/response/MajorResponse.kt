package com.dicoding.visitcampus.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "major")
data class MajorResponse(
	@field:SerializedName("faculty_id")
	val facultyId: Int,

	@field:SerializedName("major_name")
	val majorName: String,

	@field:SerializedName("accreditation_major")
	val accreditationMajor: String,

	@PrimaryKey
	@field:SerializedName("major_id")
	val majorId: Int
)
