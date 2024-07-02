package com.amlavati.presentation_post.list

import com.amlavati.domain.entity.Interaction

data class PostListModel(
    val headerText: String = "",
    val items: List<PostListItemModel> = emptyList(),
    val interaction: Interaction
)


val dummyPostList = PostListModel(
    headerText = "",
    items = mutableListOf<PostListItemModel>().apply {
        repeat(10) {
            add(
                PostListItemModel(
                    id = it.toLong(),
                    userID = it.toLong(),
                    authorName = "Author $it",
                    title = "Title $it"
                )
            )
        }
    },
    interaction = Interaction(10)
)