package com.amlavati.presentation_post.post

data class PostModel(
    val title: String,
    val body: String
)

val dummyPost = PostModel(
    "This is my tile , it will appear here",
    "This is some random body text for the title, This is given to show shimmer effect.This is some random body text for the title, This is given to show shimmer effectThis is some random body text for the title, This is given to show shimmer effect"
)