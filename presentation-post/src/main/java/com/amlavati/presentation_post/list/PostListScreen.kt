package com.amlavati.presentation_post.list

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amlavati.common.navigation.NavRoutes
import com.amlavati.common.navigation.PostInput
import com.amlavati.common.navigation.UserInput
import com.amlavati.common.state.CommonScreen
import com.amlavati.shimmer.shimmer


@Composable
fun PostListScreen(
    viewModel: PostListViewModel,
    navController: NavController
) {

    viewModel.loadPosts()
    viewModel.postList.collectAsState().value.let { state ->
        CommonScreen(
            uiState = state,
            onLoading = {
                PostList(
                    postListModel = dummyPostList,
                    isLoading = true,
                    onRowClick = {},
                    onAuthorClick = {})

            },
            onSuccess = { postListModel ->
                PostList(
                    postListModel = postListModel,
                    isLoading = false,
                    onRowClick = { item ->
                        viewModel.updateInteraction(postListModel.interaction)
                        navController.navigate(
                            NavRoutes.Post.routeForPost(
                                PostInput(item.id)
                            )
                        )
                    },
                    onAuthorClick = { item ->
                        viewModel.updateInteraction(postListModel.interaction)
                        navController.navigate(
                            NavRoutes.User.routeForUser(
                                UserInput(item.userID)
                            )
                        )
                    }
                )
            }
        )
    }
}

@Composable
fun PostList(
    postListModel: PostListModel,
    isLoading: Boolean,
    onRowClick: (PostListItemModel) -> Unit,
    onAuthorClick: (PostListItemModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        postListModel.let {
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
                    Column(modifier = Modifier.padding(16.dp)) {

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
}


