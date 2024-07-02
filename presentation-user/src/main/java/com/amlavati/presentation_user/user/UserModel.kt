package com.amlavati.presentation_user.user

data class UserModel(
    val name: String,
    val username: String,
    val email: String
)

val dummyPost = UserModel("", "", "")