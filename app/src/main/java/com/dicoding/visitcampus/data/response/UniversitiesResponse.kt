package com.dicoding.visitcampus.data.response

import com.google.gson.annotations.SerializedName


data class UniversitiesResponseItem(

	@field:SerializedName("university_id")
	val universityId: Int,

	@field:SerializedName("univ_name")
	val univName: String,

	@field:SerializedName("achievement_university")
	val achievementUniversity: List<CollegeAchievementItem>,

	@field:SerializedName("univLogo")
	val univLogo: String,

	@field:SerializedName("univCover")
	val univCover: String,

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("registration_path")
	val registrationPath: List<RegistrationPathItem>,

	@field:SerializedName("faculties")
	val faculties: List<FacultyItem>,

	@field:SerializedName("personality_univ")
	val personalityUniv: String,

	@field:SerializedName("profile_alumnus")
	val profileAlumnus: List<AlumnusProfileItem>,

	@field:SerializedName("longitude")
	val longitude: Double
)

data class CollegeAchievementItem(

	@field:SerializedName("achievement_name")
	val achievementName: String,

	@field:SerializedName("achievement_id")
	val achievementId: Int,

	@field:SerializedName("achievement_date")
	val achievementDate: String
)

data class FacultyItem(

	@field:SerializedName("faculty_name")
	val facultyName: String,

	@field:SerializedName("faculty_id")
	val facultyId: Int,

	@field:SerializedName("major")
	val major: List<MajorItem>
)

data class RegistrationPathItem(

	@field:SerializedName("path_mandiri")
	val pathMandiri: String,

	@field:SerializedName("regis_id")
	val regisId: Int,

	@field:SerializedName("detail")
	val detail: String
)

data class MajorItem(

	@field:SerializedName("major_name")
	val majorName: String,

	@field:SerializedName("accreditation_major")
	val accreditationMajor: String,

	@field:SerializedName("major_id")
	val majorId: Int
)

data class AlumnusProfileItem(

	@field:SerializedName("career")
	val career: String,

	@field:SerializedName("alumnus_name")
	val alumnusName: String,

	@field:SerializedName("cohort")
	val cohort: String,

	@field:SerializedName("alumnus_id")
	val alumnusId: Int
)
