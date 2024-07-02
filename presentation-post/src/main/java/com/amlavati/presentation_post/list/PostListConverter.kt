package com.amlavati.presentation_post.list

import android.content.Context
import com.amlavati.common.state.CommonResultConverter
import com.amlavati.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import com.amlavati.presentation_post.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PostListConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetPostsWithUsersWithInteractionUseCase.Response, PostListModel>() {

    override fun convertSuccess(data: GetPostsWithUsersWithInteractionUseCase.Response): PostListModel {

        return PostListModel(
            headerText = context.getString(
                R.string.total_click_count,
                data.interaction.totalClicks
            ),
            items = data.postUsers.map { post ->
                PostListItemModel(
                    id = post.post.id,
                    userID = post.user.id,
                    authorName = context.getString(R.string.author, post.user.name),
                    title =  context.getString(R.string.title, post.post.title),
                )
            },
            interaction = data.interaction
        )
    }
}