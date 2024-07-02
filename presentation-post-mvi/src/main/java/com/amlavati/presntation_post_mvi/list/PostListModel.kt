package com.amlavati.presntation_post_mvi.list

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
                    userId = it.toLong(),
                    authorName = "Author $it",
                    title = "Title $it"
                )
            )
        }
    },
    interaction = Interaction(10)
)

data class PostListItemModel(
    val id: Long,
    val userId: Long,
    val authorName: String,
    val title: String
)