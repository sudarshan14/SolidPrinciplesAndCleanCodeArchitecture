package com.amlavati.domain.entity


/**
 * Post with user
 *
 * @property post
 * @property user
 * @constructor Create empty Post with user
 */
data class PostWithUser(
    val post: Post,
    val user: User
)