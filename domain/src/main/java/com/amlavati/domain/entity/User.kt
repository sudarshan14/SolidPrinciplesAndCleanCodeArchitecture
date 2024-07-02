package com.amlavati.domain.entity

/**
 * User
 *
 * @property id
 * @property firstName
 * @property lastName
 * @property email
 * @constructor Create empty User
 */
data class User(
    val id: Long,
    val name: String,
    val userName: String,
    val email: String,
)
