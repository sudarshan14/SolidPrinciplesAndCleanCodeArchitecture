package com.amlavati.domain.entity

/**
 * Post
 *
 * @property id
 * @property userId
 * @property title
 * @property body
 * @constructor Create empty Post
 */
data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)
