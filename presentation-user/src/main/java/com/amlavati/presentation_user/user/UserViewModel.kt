package com.amlavati.presentation_user.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlavati.common.state.UiState
import com.amlavati.domain.usecase.GetPostUseCase
import com.amlavati.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase,
    private val userConverter: UserConverter,
) : ViewModel() {

    private val _user = MutableStateFlow<UiState<UserModel>>(UiState.Loading)
    val user: StateFlow<UiState<UserModel>> = _user

    fun loadPost(userId: Long) {
        viewModelScope.launch {
            userUseCase.execute(GetUserUseCase.Request(userId))
                .map {
                    userConverter.convert(it)
                }
                .collect {
                    _user.value = it
                }
        }
    }
}