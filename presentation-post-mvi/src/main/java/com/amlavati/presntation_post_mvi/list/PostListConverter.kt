package com.amlavati.presntation_post_mvi.list

import android.content.Context
import com.amlavati.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import com.amlavati.presentation_common_mvi.state.CommonResultConverterMvi
import com.amlavati.presntation_post_mvi.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PostListConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverterMvi<GetPostsWithUsersWithInteractionUseCase.Response, PostListModel>() {
    override fun convertSuccess(data: GetPostsWithUsersWithInteractionUseCase.Response): PostListModel {

        return PostListModel(
            headerText = context.getString(
                R.string.total_click_count,
                data.interaction.totalClicks
            ),
            items = data.postUsers.map { postWithUser ->
                PostListItemModel(
                    id = postWithUser.post.id,
                    userId = postWithUser.user.id,
                    context.getString(R.string.author, postWithUser.user.name),
                    context.getString(R.string.title, postWithUser.post.title)
                )
            },
            interaction = data.interaction
        )
    }
}