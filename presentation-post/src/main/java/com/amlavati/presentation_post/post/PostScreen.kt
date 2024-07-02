package com.amlavati.presentation_post.post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amlavati.common.navigation.PostInput
import com.amlavati.common.state.CommonScreen
import com.amlavati.shimmer.shimmer

@Composable
fun PostScreen(
    viewModel: PostViewModel,
    postInput: PostInput
) {
    LaunchedEffect(Unit) {
        viewModel.loadPost(postInput.postId)
    }

    viewModel.post.collectAsState().value.let { result ->
        CommonScreen(
            uiState = result,
            onLoading = {
                Post(
                    postModel = dummyPost,
                    isLoading = true
                )
            },
            onSuccess = { postModel ->
                Post(postModel, isLoading = false)
            })
    }
}

@Composable
fun Post(postModel: PostModel, isLoading: Boolean) {
    Column(
        modifier =
        Modifier
            .padding(8.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shimmer(isLoading = isLoading),
        ) {
            Text(
                color = Color.White,
                text = postModel.title,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                color = Color.White,
                text = postModel.body
            )
        }
    }
}