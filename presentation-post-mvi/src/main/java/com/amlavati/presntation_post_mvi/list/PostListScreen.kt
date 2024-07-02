package com.amlavati.presntation_post_mvi.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amlavati.domain.entity.Interaction
import com.amlavati.presentation_common_mvi.navigation.NavRoutesMvi
import com.amlavati.presentation_common_mvi.navigation.PostInputMvi
import com.amlavati.presentation_common_mvi.navigation.UserInputMvi
import com.amlavati.shimmer.shimmer
import kotlinx.coroutines.flow.Flow

@Composable
fun PostListScreenMvi(
    viewModel: PostListViewModelMvi,
    viewState: PostListState,
    navController: NavHostController,
    navigationEvent: Flow<PostListNavigationAction>
) {

    LaunchedEffect(Unit) {
        viewModel.performAction(PostsListUiAction.LoadPosts)
        navigationEvent.collect {
            handleNavigation(it, navController)
        }
    }

    PostList(
        viewState,
        onRowClick = { interaction, postId ->
            viewModel.performAction(PostsListUiAction.PostClicked(postId, interaction))
        },
        onAuthorClick = { interaction, userID ->
            viewModel.performAction(PostsListUiAction.AuthorClicked(userID, interaction))
        }
    )
}

@Composable
fun PostList(
    viewState: PostListState,
    onRowClick: (Interaction, Long) -> Unit,
    onAuthorClick: (Interaction, Long) -> Unit,
) {

    when (viewState) {
        PostListState.Loading -> {
            ShowList(
                postListModel = dummyPostList,
                isLoading = true,
                onRowClick = {},
                onAuthorClick = {})
        }

        is PostListState.Error -> {
            Text(text = viewState.error.toString())
        }

        is PostListState.Success -> {
            val postListModel = viewState.data
            ShowList(
                postListModel = postListModel,
                isLoading = false,
                onRowClick = { item ->
                    onRowClick(postListModel.interaction, item.id)
                },
                onAuthorClick = { item ->
                    onAuthorClick(postListModel.interaction, item.userId)
                }
            )
        }
    }

}

@Composable
fun ShowList(
    postListModel: PostListModel,
    isLoading: Boolean,
    onRowClick: (PostListItemModel) -> Unit,
    onAuthorClick: (PostListItemModel) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item(postListModel.headerText) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = postListModel.headerText)
            }
        }
        items(postListModel.items) { item ->
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp,
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        onRowClick(item)
                    }
                    .shimmer(isLoading = isLoading)
            ) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onRowClick(item)
                    }) {
                    ClickableText(
                        text = AnnotatedString(text = item.authorName),
                        onClick = {
                            onAuthorClick(item)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(
                        modifier = Modifier.padding(
                            8.dp
                        )
                    )
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

        }
    }
}

fun handleNavigation(it: PostListNavigationAction, navController: NavHostController) {
    when (it) {
        is PostListNavigationAction.AuthorClicked -> {
            navController.navigate(
                NavRoutesMvi.User.routeForUser(
                    UserInputMvi(userId = it.userId)
                )
            )
        }

        is PostListNavigationAction.PostClicked -> {
            navController.navigate(
                NavRoutesMvi.Post.routeForPost(
                    PostInputMvi(postId = it.postId)
                )
            )

        }
    }
}



