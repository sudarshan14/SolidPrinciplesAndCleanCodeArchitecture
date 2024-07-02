package com.amlavati.presntation_post_mvi.list

import com.amlavati.presentation_common_mvi.state.NavigationEvent

sealed class PostListNavigationAction : NavigationEvent {
    data class PostClicked(val postId: Long) : PostListNavigationAction()
    data class AuthorClicked(val userId: Long) : PostListNavigationAction()

}