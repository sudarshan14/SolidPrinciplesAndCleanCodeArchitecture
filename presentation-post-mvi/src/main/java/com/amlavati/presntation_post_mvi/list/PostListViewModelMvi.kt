package com.amlavati.presntation_post_mvi.list

import androidx.lifecycle.viewModelScope
import com.amlavati.domain.entity.Interaction
import com.amlavati.domain.entity.Result
import com.amlavati.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import com.amlavati.domain.usecase.UpdateInteractionUseCase
import com.amlavati.presentation_common_mvi.navigation.NavRoutesMvi
import com.amlavati.presentation_common_mvi.navigation.UserInputMvi
import com.amlavati.presentation_common_mvi.state.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModelMvi @Inject constructor(
    private val getPostsUseCase: GetPostsWithUsersWithInteractionUseCase,
    private val updateInteractionUseCase: UpdateInteractionUseCase,
    private val converter: PostListConverter
) : MviViewModel<PostListState, PostsListUiAction, PostListNavigationAction>() {


    override fun createInitialState(): PostListState {
        return PostListState.Loading
    }

    override fun handleAction(action: PostsListUiAction) {
        when (action) {
            is PostsListUiAction.AuthorClicked -> {

                updateInteraction(action.interaction)

                sendNavigationEvent(PostListNavigationAction.AuthorClicked(action.userId))
            }

            is PostsListUiAction.LoadPosts -> {
                executeLoadPost()
            }

            is PostsListUiAction.PostClicked -> {
                updateInteraction(action.interaction)
                sendNavigationEvent(PostListNavigationAction.PostClicked(action.postId))
            }
        }
    }


    private fun executeLoadPost() {
        viewModelScope.launch {
            getPostsUseCase.execute(GetPostsWithUsersWithInteractionUseCase.Request)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            submitUiState(PostListState.Success(converter.convertSuccess(result.data)))
                        }

                        is Result.Error -> {
                            submitUiState(PostListState.Error(result.exception.localizedMessage.orEmpty()))
                        }
                    }
                }
        }
    }

    private fun updateInteraction(interaction: Interaction) {
        viewModelScope.launch {
            updateInteractionUseCase.execute(
                UpdateInteractionUseCase.Request(
                    interaction.copy(
                        totalClicks = interaction.totalClicks + 1
                    )
                )
            ).collect()
        }
    }
}