package com.amlavati.presentation_post.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlavati.common.state.UiState
import com.amlavati.domain.usecase.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
    private val postUseCase: GetPostUseCase,
    private val postConverter: PostConverter,
) : ViewModel() {

    private val _post = MutableStateFlow<UiState<PostModel>>(UiState.Loading)
    val post: StateFlow<UiState<PostModel>> = _post

    fun loadPost(postId: Long) {
        viewModelScope.launch {
            postUseCase.execute(GetPostUseCase.Request(postId)).collect {
                _post.value = postConverter.convert(it)
            }

//            postUseCase.execute(GetPostUseCase.Request(postId))
//                .map {
//                    postConverter.convert(it)
//                }
//                .collect {
//                    _post.value = it
//                }
        }
    }
}