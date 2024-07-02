package com.amlavati.presentation_post.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlavati.common.state.UiState
import com.amlavati.domain.entity.Interaction
import com.amlavati.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import com.amlavati.domain.usecase.UpdateInteractionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsWithUsersWithInteractionUseCase,
    private val updateInteractionUseCase: UpdateInteractionUseCase,
    private val postListConverter: PostListConverter
) : ViewModel() {

    private val _postList = MutableStateFlow<UiState<PostListModel>>(UiState.Loading)
    val postList: StateFlow<UiState<PostListModel>> = _postList

    fun loadPosts() {
        viewModelScope.launch {
            useCase.execute(GetPostsWithUsersWithInteractionUseCase.Request)
                .map {
                    postListConverter.convert(it)
                }
                .collect {
                    _postList.value = it
                }
        }
    }


    fun updateInteraction(interaction: Interaction) {
        viewModelScope.launch {
            updateInteractionUseCase.execute(
                UpdateInteractionUseCase.Request(
                    interaction.copy(totalClicks = interaction.totalClicks + 1)
                )
            ).collect()
        }
    }
}