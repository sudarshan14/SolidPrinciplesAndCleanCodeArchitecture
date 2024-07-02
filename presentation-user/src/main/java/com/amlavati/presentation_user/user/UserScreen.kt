package com.amlavati.presentation_user.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amlavati.common.navigation.UserInput
import com.amlavati.common.state.CommonScreen
import com.amlavati.shimmer.shimmer

@Composable
fun UserScreen(
    viewModel: UserViewModel,
    postInput: UserInput
) {
    viewModel.loadPost(postInput.userId)
    viewModel.user.collectAsState().value.let { result ->
        CommonScreen(
            uiState = result,
            onLoading = {
                User(
                    userModel = dummyPost,
                    isLoading = true
                )
            },
            onSuccess = { postModel ->
                User(postModel, isLoading = false)
            })
    }
}

@Composable
fun User(userModel: UserModel, isLoading: Boolean) {
    Column(
        modifier =
        Modifier
            .padding(16.dp)
            .shimmer(isLoading = isLoading)
    ) {
        Text(text = userModel.name)
        Text(text = userModel.username)
        Text(text = userModel.email)
    }
}