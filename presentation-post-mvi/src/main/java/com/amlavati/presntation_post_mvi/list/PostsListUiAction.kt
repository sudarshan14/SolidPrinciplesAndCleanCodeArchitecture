package com.amlavati.presntation_post_mvi.list

import com.amlavati.domain.entity.Interaction
import com.amlavati.presentation_common_mvi.state.UiAction

sealed class PostsListUiAction : UiAction {
    data object LoadPosts : PostsListUiAction()
    data class PostClicked(val postId: Long, val interaction: Interaction) : PostsListUiAction()
    data class AuthorClicked(val userId: Long,val interaction: Interaction) : PostsListUiAction()
}