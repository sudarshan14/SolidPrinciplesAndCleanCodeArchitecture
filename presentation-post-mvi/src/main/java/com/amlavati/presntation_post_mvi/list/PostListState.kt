package com.amlavati.presntation_post_mvi.list

import androidx.compose.runtime.State
import com.amlavati.presentation_common_mvi.state.UiState

sealed class PostListState : UiState {
    data object Loading : PostListState()
    data class Success(val data: PostListModel) : PostListState()
    data class Error(val error: Any) : PostListState()
}
