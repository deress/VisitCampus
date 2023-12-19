package com.dicoding.visitcampus.data.pref

class UserModel (
    val email: String,
    val token: String,
    val userId : String,
    val name : String,
    val isLogin: Boolean = false
)