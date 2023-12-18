package com.dicoding.visitcampus.data.response

import com.google.gson.annotations.SerializedName

data class DetailUnivResponse(

	@field:SerializedName("logoPhoto")
	val logoPhoto: String,

	@field:SerializedName("alumnusProfile")
	val alumnusProfile: List<AlumnusProfileItem>,

	@field:SerializedName("univDescription")
	val univDescription: String,

	@field:SerializedName("univName")
	val univName: String,

	@field:SerializedName("collegeAchievement")
	val collegeAchievement: List<CollegeAchievementItem>,

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("coverPhoto")
	val coverPhoto: String,

	@field:SerializedName("registrationPath")
	val registrationPath: List<RegistrationPathItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("longitude")
	val longitude: Double,

	@field:SerializedName("faculty")
	val faculty: List<FacultyItem>
)

data class AlumnusProfileItem(

	@field:SerializedName("alumnusPhoto")
	val alumnusPhoto: String,

	@field:SerializedName("alumnusCareer")
	val alumnusCareer: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("alumnusName")
	val alumnusName: String,

	@field:SerializedName("alumnusCohort")
	val alumnusCohort: String
)

data class CollegeAchievementItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("achievementName")
	val achievementName: String,

	@field:SerializedName("achievementDate")
	val achievementDate: String
)

data class MajorItem(

	@field:SerializedName("majorAccreditation")
	val majorAccreditation: String,

	@field:SerializedName("majorDegree")
	val majorDegree: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("majorName")
	val majorName: String
)

data class FacultyItem(

	@field:SerializedName("major")
	val major: List<MajorItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("facultyName")
	val facultyName: String
)

data class RegistrationPathItem(

	@field:SerializedName("pathName")
	val pathName: String,

	@field:SerializedName("pathDescription")
	val pathDescription: String,

	@field:SerializedName("id")
	val id: Int
)
